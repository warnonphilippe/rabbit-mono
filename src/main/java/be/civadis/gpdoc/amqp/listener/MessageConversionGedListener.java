package be.civadis.gpdoc.amqp.listener;

import be.civadis.gpdoc.amqp.config.AmqpConvertQueuesBizConfiguration;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import be.civadis.gpdoc.dto.ConversionGedMessageDTO;
import be.civadis.gpdoc.multitenancy.TenantContext;

/**
 * Listener
 */
@Component
public class MessageConversionGedListener extends AbstractMessageListener {

    public MessageConversionGedListener(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }


    @RabbitListener(
        queues = AmqpConvertQueuesBizConfiguration.CONVERT_GED_QUEUE_NAME,
        concurrency = "3-10")  // https://docs.spring.io/spring-amqp/docs/current/reference/html/#listener-concurrency
    // @HystrixCommand(fallbackMethod = "fallbackMessage") // pour activer un circuit breaker
        public void onConversionMessage(@Payload ConversionGedMessageDTO dto) {

        System.out.println("Tenant " + TenantContext.getCurrentTenant() + " : ticket conversion received : " + dto.toString());

        try {

            // TODO traitement du ticket
            // appel de documentConversionService...
            // celui-ci devra effectué les traitements et mettre le ticket à jour
            // en cas d'erreur, le service devra mettre aussi le ticket à jour et lancer une exception afin de prévenir le listener

            // simuler temps de conversion
            Thread.sleep(5000);

        } catch (Exception ex){
            // exception pour signaler l'échec du traitement du message, mais sans requeing
            throw new AmqpRejectAndDontRequeueException("Erreur lors du traitement du ticket de conversion : " + dto.toString(), ex);
        }
    }

    //Dans le cas d'une conversion ou d'un merge, on cas d'erreur, on la signale dans le ticket
    //Pour les envois ebox, on doit implémenter un réessai après un temps d'attente
    // -> transférer le message dans une queue d'attente (si < nb max d'essai)
    // -> après un temps d'attente dans la queue, retransfert vers le queue de traitement

}
