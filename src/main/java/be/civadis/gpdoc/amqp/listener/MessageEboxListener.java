package be.civadis.gpdoc.amqp.listener;

import be.civadis.gpdoc.amqp.config.AmqpConvertQueuesBizConfiguration;
import be.civadis.gpdoc.amqp.config.AmqpEboxQueuesBizConfiguration;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import be.civadis.gpdoc.dto.EboxMessageDTO;
import be.civadis.gpdoc.dto.TicketConversionDTO;
import be.civadis.gpdoc.multitenancy.TenantContext;

/**
 * Listener
 */
@Component
public class MessageEboxListener extends AbstractMessageListener {

    public MessageEboxListener(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @RabbitListener(
        queues = AmqpEboxQueuesBizConfiguration.EBOX_QUEUE_NAME,
        concurrency = "3-10")  // https://docs.spring.io/spring-amqp/docs/current/reference/html/#listener-concurrency
    // @HystrixCommand(fallbackMethod = "fallbackMessage") // pour activer un circuit breaker
        public void onEboxMessage(@Payload EboxMessageDTO dto) {

        System.out.println("Tenant " + TenantContext.getCurrentTenant() + " : ticket conversion received : " + dto.toString());

        try {

            // TODO traitement du ticket
            // appel de documentEboxService...
            // celui-ci devra effectué les traitements et mettre le ticket à jour
            // en cas d'erreur, le service devra mettre aussi le ticket à jour et lancer une exception afin de prévenir le listener

            // simuler temps d'envoi ebox
            Thread.sleep(5000);

        } catch (Exception ex){ //catcher erreur retryable
            //log error
            ex.printStackTrace();

            // exception pour signaler l'échec du traitement du message, mais sans requeing
            // throw new AmqpRejectAndDontRequeueException("Erreur lors du traitement du ticket de ebox : " + dto.toString());

            // si on veut réessayer ke plus tot possible, lancer un type d'exception qui entraîne un requeing
            // throw new Exception("Erreur lors du traitement du ticket de conversion : " + message.toString());

            // autre solution, envoi du message vers une retryQueue (doit alors être activée dans la config)
            // TODO : limiter le nombre de retrys
            retryMessage(dto);
        }

        // TODO catch erreur not retryable
    }

    //Dans le cas d'une conversion ou d'un merge, on cas d'erreur, on la signale dans le ticket
    //Pour les envois ebox, on doit implémenter un réessai après un temps d'attente
    // -> transférer le message dans une queue d'attente (si < nb max d'essai)
    // -> après un temps d'attente dans la queue, retransfert vers le queue de traitement

}
