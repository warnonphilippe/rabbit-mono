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

    public static final String ENVOI_EBOX_QUEUE_NAME = "envoiEboxQueue";
    public static final String ENVOI_EBOX_EXCHANGE_NAME = "envoiEboxExchange";
    public static final String ENVOI_EBOX_ROUTING_KEY = "envoiEbox";

    public final static String RETRY_ENVOI_EBOX_EXCHANGE = "retryEnvoiEboxExchange";
    public final static String RETRY_ENVOI_EBOX_QUEUE_NAME = "retryEnvoiEboxQueue";


    @Bean
    public Exchange envoiEboxExchange() {
        return ExchangeBuilder.fanoutExchange(ENVOI_EBOX_EXCHANGE_NAME).build();
    }

    @Bean
    public Queue envoiEboxQueue() {
        return QueueBuilder.durable(ENVOI_EBOX_QUEUE_NAME).build();
    }

    // si on veut rediriger les messages en erreur vers un retryQueue

    @Bean
    public Exchange retryEnvoiEboxExchange() {
        return ExchangeBuilder.fanoutExchange(RETRY_ENVOI_EBOX_EXCHANGE).build();
    }

    @Bean
    public Queue retryEnvoiEboxQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-dead-letter-exchange", ENVOI_EBOX_EXCHANGE_NAME);
        args.put("x-message-ttl", 30000);
        return new Queue(RETRY_ENVOI_EBOX_QUEUE_NAME,true,false,false, args);
    }

    @Bean
    public List<Binding> eboxBindings() {
        return Arrays.asList(
            BindingBuilder.bind(envoiEboxQueue()).to(envoiEboxExchange()).with("*").noargs(),
            BindingBuilder.bind(retryEnvoiEboxQueue()).to(retryEnvoiEboxExchange()).with("*").noargs()
        );
    }


}
