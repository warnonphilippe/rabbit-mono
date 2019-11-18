package be.civadis.gpdoc.amqp;

import java.io.IOException;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
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

    // TODO liaison ged
    // TODO get ged, exécution de la conversion, stockage ged

    @RabbitListener(
        queues = AMQPConvertConfig.CONVERT_QUEUE_NAME, 
        concurrency = "3-10")  // https://docs.spring.io/spring-amqp/docs/current/reference/html/#listener-concurrency
    // @HystrixCommand(fallbackMethod = "fallbackMessage") // pour activer un circuit breaker
    public void processConvertMessage(Message message) {

        TicketConversionDto tc = getContent(message, TicketConversionDto.class);
        System.out.println("ticket conversion received : " + tc.toString());

        try {

            // TODO get document from GED

            // TODO conversion

            // TODO save converted file in GED

            // TODO MAJ ticket (success)

            // simuler temps de conversion
            Thread.sleep(5000);

        } catch (Exception ex){
            ex.printStackTrace();
            // TODO MAJ ticket (error)

            // exception pour signaler l'échec du traitement du message, mais sans requeing
            throw new AmqpRejectAndDontRequeueException("Erreur lors du traitement du ticket de conversion : " + message.toString());
            
            // si on veut réessayer, lancer un type d'exception qui entraîne un requeing
            // throw new Exception("Erreur lors du traitement du ticket de conversion : " + message.toString());
            // autre solution, envoi du message vers une retryQueue (doit alors être activée dans la config)
            // retryMessage(message, getType(message));
        }
    }

    // TODO : A discuter
    //      gestion d'erreur : retry, error, ... voir params spring boot, dlq, ...
    //      queue pour traitement rapide et prioritaire ?
    
}
