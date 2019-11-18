package be.civadis.gpdoc.amqp;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AMQPConfig
 */
@Configuration
public class AMQPConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());   
        factory.setAdviceChain(customRabbitListenerAroundAdvice());
        //factory.setTaskExecutor(threadPoolTaskExecutor());
        //factory.setMaxConcurrentConsumers(10);
        //factory.setConcurrentConsumers(3);
        return factory;
    }

    @Bean
    public CustomRabbitListenerAroundAdvice customRabbitListenerAroundAdvice() {
        return new CustomRabbitListenerAroundAdvice();
    }
/*
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }
*/
    // https://docs.spring.io/spring-amqp/docs/current/reference/html/#_introduction
    // https://stackoverflow.com/questions/42938118/spring-amqp-rabbitlistener-convert-to-origin-object
    // https://docs.spring.io/spring-amqp/docs/current/reference/html/#listener-concurrency
    // https://stackoverflow.com/questions/34513662/how-do-we-hook-into-before-after-message-processing-using-rabbitlistener

}
