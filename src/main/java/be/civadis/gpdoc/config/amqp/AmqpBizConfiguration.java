package be.civadis.gpdoc.config.amqp;

import be.civadis.gpdoc.multitenancy.TenantContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AmqpBizConfiguration {

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter());
        factory.setAdviceChain(gpdocRabbitListenerAroundAdvice());
        return factory;
    }

    @Primary
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Primary
    @Bean
    public RabbitListenerBizAroundAdvice gpdocRabbitListenerAroundAdvice() {
        return new RabbitListenerBizAroundAdvice();
    }

    @Primary
    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) {
                if (bean instanceof RabbitTemplate) {
                    ((RabbitTemplate) bean).setBeforePublishPostProcessors(message -> {
                        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        message.getMessageProperties().setHeader("tenant", TenantContext.getCurrentTenant());
                        message.getMessageProperties().setHeader("application", TenantContext.getCurrentApp());
                        return message;
                    });
                }
                return bean;
            }
        };
    }

    /*
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }
*/

    /*
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }
*/

}

    // https://docs.spring.io/spring-amqp/docs/current/reference/html/#_introduction
    // https://stackoverflow.com/questions/42938118/spring-amqp-rabbitlistener-convert-to-origin-object
    // https://docs.spring.io/spring-amqp/docs/current/reference/html/#listener-concurrency
    // https://stackoverflow.com/questions/34513662/how-do-we-hook-into-before-after-message-processing-using-rabbitlistener


