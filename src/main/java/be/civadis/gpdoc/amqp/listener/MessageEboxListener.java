package be.civadis.gpdoc.amqp.listener;

import be.civadis.gpdoc.amqp.config.AmqpEboxQueuesBizConfiguration;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import be.civadis.gpdoc.dto.EnvoiEboxMessageDTO;
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
    // @HystrixCommand(fallbackMethod = "fallbackMessage") // pour activer un circuit breaker
        public void onEnvoiEboxMessage(@Payload EnvoiEboxMessageDTO dto) {

        System.out.println("Tenant " + TenantContext.getCurrentTenant() + " : ticket ebox received : " + dto.toString());

        try {

            // TODO traitement du ticket
            // appel de documentEboxService...
            // celui-ci devra effectué les traitements et mettre le ticket à jour
            // en cas d'erreur, le service devra mettre aussi le ticket à jour et lancer une exception afin de prévenir le listener

            // simuler temps d'envoi ebox avec retry
            Thread.sleep(5000);
            throw new EboxRetryableException("test erreur retryable");

        } catch (EboxRetryableException ex){
            // envoi du message vers une retryQueue
            if (dto.getTicketEbox().getEssais() < 3){
                retryMessage(dto);
            } else {
                throw new AmqpRejectAndDontRequeueException("Erreur lors du traitement du ticket de ebox (après ré-essais: " + dto.toString(), ex);
            }

        } catch (Exception ex){
            // exception pour signaler l'échec du traitement du message, mais sans requeing
            throw new AmqpRejectAndDontRequeueException("Erreur lors du traitement du ticket de ebox : " + dto.toString(), ex);
        }


    }

}
