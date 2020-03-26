package be.civadis.gpdoc.amqp.listener;

import be.civadis.gpdoc.amqp.config.AmqpConvertQueuesBizConfiguration;
import be.civadis.gpdoc.amqp.config.AmqpEboxQueuesBizConfiguration;
import be.civadis.gpdoc.amqp.exception.EboxRetryableException;

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

            // simuler temps d'envoi ebox avec retry
            Thread.sleep(5000);
            throw new EboxRetryableException("test erreur retryable");

        } catch (EboxRetryableException ex){
            // envoi du message vers une retryQueue (doit alors être activée dans la config)
            // TODO : limiter le nombre de retrys
            retryMessage(dto);

        } catch (Exception ex){
            // exception pour signaler l'échec du traitement du message, mais sans requeing
            throw new AmqpRejectAndDontRequeueException("Erreur lors du traitement du ticket de ebox : " + dto.toString(), ex);
        }


    }

}