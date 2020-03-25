package be.civadis.gpdoc.amqp.listener;

import be.civadis.gpdoc.amqp.config.AmqpConvertQueuesBizConfiguration;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import be.civadis.gpdoc.dto.TicketConversionDto;
import be.civadis.gpdoc.multitenancy.TenantContext;

/**
 * Listener
 */
@Component
public class MessageConversionListener extends AbstractMessageListener {

    public MessageConversionListener(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @RabbitListener(
        queues = AmqpConvertQueuesBizConfiguration.CONVERT_QUEUE_NAME,
        concurrency = "3-10")  // https://docs.spring.io/spring-amqp/docs/current/reference/html/#listener-concurrency
    // @HystrixCommand(fallbackMethod = "fallbackMessage") // pour activer un circuit breaker
        public void processConvertMessage(@Payload TicketConversionDto tc) {

        System.out.println("Tenant " + TenantContext.getCurrentTenant() + " : ticket conversion received : " + tc.toString());

        try {

            // TODO traitement du ticket

            // exemple, si conversion d'un fichier de la ged
            //      get document
            //      conversion
            //      save document

            // TODO MAJ ticket (success)

            // simuler temps de conversion
            Thread.sleep(5000);

        } catch (Exception ex){
            //log error
            ex.printStackTrace();

            // TODO MAJ ticket (error)

            // exception pour signaler l'échec du traitement du message, mais sans requeing
            throw new AmqpRejectAndDontRequeueException("Erreur lors du traitement du ticket de conversion : " + tc.toString());

            // si on veut réessayer, lancer un type d'exception qui entraîne un requeing
            // throw new Exception("Erreur lors du traitement du ticket de conversion : " + message.toString());
            // autre solution, envoi du message vers une retryQueue (doit alors être activée dans la config)
            // retryMessage(message, getType(message));
        }
    }

    //Dans le cas d'une conversion ou d'un merge, on cas d'erreur, on la signale dans le ticket
    //Pour les envois ebox, on doit implémenter un réessai après un temps d'attente
    // -> transférer le message dans une queue d'attente (si < nb max d'essai)
    // -> après un temps d'attente dans la queue, retransfert vers le queue de traitement

}
