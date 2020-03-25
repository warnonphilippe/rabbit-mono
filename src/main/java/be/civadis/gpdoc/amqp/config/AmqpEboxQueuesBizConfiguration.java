package be.civadis.gpdoc.amqp.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AmqpEboxQueuesBizConfiguration {

    public static final String EBOX_QUEUE_NAME = "eboxQueue";
    public static final String EBOX_EXCHANGE_NAME = "eboxExchange";
    public static final String EBOX_ROUTING_KEY = "ebox";

    public final static String RETRY_EBOX_EXCHANGE_NAME = "retryEboxExchange";
    public final static String RETRY_EBOX_QUEUE_NAME = "retryEboxQueue";

    public final static String ERROR_EBOX_EXCHANGE_NAME = "errorEboxExchange";
    public final static String ERROR_EBOX_QUEUE_NAME = "errorEboxQueue";


    @Bean
    public Exchange eboxExchange() {
        return ExchangeBuilder.fanoutExchange(EBOX_EXCHANGE_NAME).build();
    }

    @Bean
    public Queue eboxQueue() {
        return QueueBuilder.durable(EBOX_QUEUE_NAME).build();
    }

    /*
    @Bean
    public Binding eboxBinding() {
        return BindingBuilder.bind(eboxQueue()).to(eboxExchange()).with("*").noargs();
    }
    */

    // si on veut rediriger les messages en erreur vers un retryQueue

    @Bean
    public Exchange retryEboxExchange() {
        return ExchangeBuilder.fanoutExchange(RETRY_EBOX_EXCHANGE_NAME).build();
    }

    @Bean
    public Queue retryEboxQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-dead-letter-exchange", EBOX_EXCHANGE_NAME);
        args.put("x-message-ttl", 30000);
        return new Queue(RETRY_EBOX_QUEUE_NAME,true,false,false, args);
    }

    //si on veut placer les messages en erreurs dans une queues d'erreurs


    @Bean
    public Exchange errorEboxExchange() {
        return ExchangeBuilder.fanoutExchange(ERROR_EBOX_EXCHANGE_NAME).build();
	}

    @Bean
    public Queue errorEboxQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-message-ttl", 30000);
        return new Queue(ERROR_EBOX_QUEUE_NAME,true,false,false, args);
	}

    @Bean
    public List<Binding> eboxBindings() {
        return Arrays.asList(
            BindingBuilder.bind(eboxQueue()).to(eboxExchange()).with("*").noargs(),
            BindingBuilder.bind(retryEboxQueue()).to(retryEboxExchange()).with("*").noargs(),
            BindingBuilder.bind(errorEboxQueue()).to(errorEboxExchange()).with("*").noargs()
        );
    }


}
