package be.civadis.gpdoc.federation.amqp.listener;

import be.civadis.gpdoc.config.amqp.AmqpEboxQueuesBizConfiguration;

import be.civadis.gpdoc.domain.TicketEnvoiEbox;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import be.civadis.gpdoc.multitenancy.TenantContext;
import be.civadis.gpdoc.service.exception.EboxRetryableException;

/**
 * Listener
 */
@Component
public class MessageEboxListener extends AbstractMessageListener {

    public MessageEboxListener(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @RabbitListener(
        queues = AmqpEboxQueuesBizConfiguration.ENVOI_EBOX_QUEUE_NAME,
        concurrency = "3-10")  // https://docs.spring.io/spring-amqp/docs/current/reference/html/#listener-concurrency
        public void onEnvoiEboxMessage(@Payload TicketEnvoiEbox ticketEbox) {

        System.out.println("Tenant " + TenantContext.getCurrentTenant() + " : ticket ebox received : " + ticketEbox.toString());

        try {

            // TODO traitement du ticket
            // appel de documentEboxService...
            // celui-ci devra effectuer les traitements et mettre le ticket à jour
            // en cas d'erreur, le service devra mettre aussi le ticket à jour et lancer une exception afin de prévenir le listener

            // simuler temps d'envoi ebox avec retry
            Thread.sleep(5000);
            throw new EboxRetryableException("test erreur retryable");

        } catch (EboxRetryableException ex){
            // envoi du message vers une retryQueue
            if (ticketEbox.getEssais() < 3){
                retryMessage(ticketEbox);
            } else {
                throw new AmqpRejectAndDontRequeueException("Erreur lors du traitement du ticket de ebox (après ré-essais: " + ticketEbox.toString(), ex);
            }

        } catch (Exception ex){
            // exception pour signaler l'échec du traitement du message, mais sans requeing
            throw new AmqpRejectAndDontRequeueException("Erreur lors du traitement du ticket de ebox : " + ticketEbox.toString(), ex);
        }


    }

}
