package be.civadis.gpdoc.amqp.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AmqpConvertQueuesBizConfiguration {

    public static final String CONVERT_QUEUE_NAME = "convertQueue";
    public static final String CONVERT_EXCHANGE_NAME = "convertExchange";
    public static final String CONVERT_ROUTING_KEY = "convert";

    public static final String CONVERT_GED_QUEUE_NAME = "convertGedQueue";
    public static final String CONVERT_GED_EXCHANGE_NAME = "convertGedExchange";
    public static final String CONVERT_GED_ROUTING_KEY = "convertGed";

    @Bean
    public Exchange convertExchange() {
        return ExchangeBuilder.fanoutExchange(CONVERT_EXCHANGE_NAME).build();
    }

    @Bean
    public Queue convertQueue() {
        return QueueBuilder.durable(CONVERT_QUEUE_NAME).build();
    }

    @Bean
    public Exchange convertGedExchange() {
        return ExchangeBuilder.fanoutExchange(CONVERT_GED_EXCHANGE_NAME).build();
    }

    @Bean
    public Queue convertGedQueue() {
        return QueueBuilder.durable(CONVERT_GED_QUEUE_NAME).build();
    }

    @Bean
    public Binding convertBinding() {
        return BindingBuilder.bind(convertQueue()).to(convertExchange()).with("*").noargs();
    }

    @Bean
    public List<Binding> convertBindings() {
        return Arrays.asList(
            BindingBuilder.bind(convertQueue()).to(convertExchange()).with("*").noargs(),
            BindingBuilder.bind(convertGedQueue()).to(convertGedExchange()).with("*").noargs()
        );
    }

}
