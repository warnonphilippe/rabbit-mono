package be.civadis.gpdoc.amqp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.civadis.gpdoc.config.ApplicationProperties;

/**
 * AMQPConvertConfig
 */
@Configuration
public class AMQPConvertConfig {

    public final static String CONVERT_QUEUE_NAME = "convertQueue";
    public final static String CONVERT_EXCHANGE_NAME = "convertExchange";
    public final static String CONVERT_ROUTING_KEY = "convert";

    //public final static String RETRY_CONVERT_EXCHANGE_NAME = "retryConvertExchange";
    //public final static String RETRY_CONVERT_QUEUE_NAME = "retryConvertQueue";

    //public final static String ERROR_CONVERT_EXCHANGE_NAME = "errorConvertExchange";
    //public final static String ERROR_CONVERT_QUEUE_NAME = "retryConvertQueue";
    
    @Autowired
	private ApplicationProperties applicationProperties;

	@Bean
	public Exchange convertExchange() {
        return ExchangeBuilder.fanoutExchange(CONVERT_EXCHANGE_NAME).build();
    }
    
    @Bean
	public Queue convertQueue() {    
        Map<String, Object> args = new HashMap<String, Object>();
        //args.put("x-dead-letter-exchange", AMQPConvertConfig.ERROR_CONVERT_EXCHANGE_NAME);
        //args.put("x-message-ttl", 600000);
        return new Queue(CONVERT_QUEUE_NAME,true,false,false, args);
    }
    
    @Bean
    public List<Binding> convertBindings() {
        return Arrays.asList(
            BindingBuilder.bind(convertQueue()).to(convertExchange()).with("*").noargs()
            //BindingBuilder.bind(retryConvertQueue()).to(retryConvertExchange()).with("*").noargs(),
            //BindingBuilder.bind(errorConvertQueue()).to(errorConvertExchange()).with("*").noargs()
        );
    }

    // si on veut rediriger les messages en erreur vers un retryQueue
/*
    @Bean
    public Exchange retryConvertExchange() {
        return ExchangeBuilder.fanoutExchange(RETRY_CONVERT_EXCHANGE_NAME).build();
    }

    @Bean
    public Queue retryConvertQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-dead-letter-exchange", CONVERT_EXCHANGE_NAME);
        args.put("x-message-ttl", 10000);
        return new Queue(RETRY_CONVERT_QUEUE_NAME,true,false,false, args);
    }
*/	
    //si on veut placer les messages en erreurs dans une queues d'erreurs

/*
    @Bean
    public Exchange errorConvertExchange() {
        return ExchangeBuilder.fanoutExchange(ERROR_CONVERT_EXCHANGE_NAME).build();
	}

    @Bean
    public Queue errorConvertQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-message-ttl", 10000);
        return new Queue(ERROR_CONVERT_QUEUE_NAME,true,false,false, args);
	}
*/
    

}