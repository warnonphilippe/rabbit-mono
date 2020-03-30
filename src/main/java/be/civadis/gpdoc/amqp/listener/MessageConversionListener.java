package be.civadis.gpdoc.amqp.listener;

import be.civadis.gpdoc.amqp.config.AmqpConvertQueuesBizConfiguration;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import be.civadis.gpdoc.amqp.dto.ConversionMessageDTO;
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
        public void onConversionMessage(@Payload ConversionMessageDTO dto) {

        System.out.println("Tenant " + TenantContext.getCurrentTenant() + " : ticket conversion received : " + dto.toString());

        try {

            // TODO traitement du ticket
            // appel de documentConversionService...
            // celui-ci devra effectuer les traitements et mettre le ticket à jour
            // en cas d'erreur, le service devra mettre aussi le ticket à jour et lancer une exception afin de prévenir le listener
            // le dto contient soit l'id du fichier dans la ged, soit le path du fichier temporaire (cas fichier uploadé pas en ged)
            // ainsi que le ticket

            // simuler temps de conversion
            Thread.sleep(5000);

        } catch (Exception ex){
            // exception pour signaler l'échec du traitement du message, mais sans requeing
            throw new AmqpRejectAndDontRequeueException("Erreur lors du traitement du ticket de conversion : " + dto.toString(), ex);
        }
    }

}
