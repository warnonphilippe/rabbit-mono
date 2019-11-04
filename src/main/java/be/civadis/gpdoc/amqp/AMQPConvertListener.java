package be.civadis.gpdoc.amqp;

import java.io.IOException;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import be.civadis.gpdoc.dto.TicketConversionDto;

/**
 * Listener
 */
@Component
public class AMQPConvertListener extends AMQPAbstractListener {

    public AMQPConvertListener(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    // TODO : pouvoir lancer N listeners en //, ex : concurrency = "3-10"
    // TODO : pouvoir atteindre rabbit sur k8s
    // TODO revoir trt retry, error, ... existe params spring boot
    // TODO : queue pour traitement rapide et prioritaire

    @RabbitListener(queues = AMQPConvertConfig.CONVERT_QUEUE_NAME, concurrency = "3-10")
    // @HystrixCommand(fallbackMethod = "fallbackMessage")
    public void processConvertMessage(Object message) {

        TicketConversionDto tc = getContent(message, TicketConversionDto.class);
        System.out.println("ticket conversion received : " + tc.toString());

        try {
            // TODO traiter le TicketConversion

        } catch (Exception ex){
            // TODO : traiter erreur de conversion (-> infos dans ticket)

        }

        // test echec mais pas de retraitement (ex lancée si erreur de désérializarion du message)
        // throw new AmqpRejectAndDontRequeueException("Test transmission dans dead queue letter");
        // test requeueing
        // throw new Exception("Test reject Message");

    }
    
}