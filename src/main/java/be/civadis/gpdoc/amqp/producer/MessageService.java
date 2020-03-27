package be.civadis.gpdoc.amqp.producer;

import be.civadis.gpdoc.amqp.common.AbstractMessageService;
import be.civadis.gpdoc.amqp.config.AmqpConvertQueuesBizConfiguration;
import be.civadis.gpdoc.amqp.dto.ConversionMessageDTO;
import be.civadis.gpdoc.domain.TicketConversion;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends AbstractMessageService {

    public MessageService(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    /**
     * Envoyer un message à RabbitMQ contenant un ticketConversion, ce ticket sera traité en asynchrone par un listener dès que le message lui aura été remi
     *
     * @param conv ticket contenant les infos de la conversion demandée
     * @throws Exception
     */
    public void envoyerMessageConversion(TicketConversion conv, String fichierAConvertirLocalPath, String fichierAConvertirDocumentUuid) {
        ConversionMessageDTO msg = new ConversionMessageDTO(conv, fichierAConvertirLocalPath, fichierAConvertirDocumentUuid);
        this.convertAndSend(AmqpConvertQueuesBizConfiguration.CONVERT_EXCHANGE_NAME, AmqpConvertQueuesBizConfiguration.CONVERT_ROUTING_KEY, msg);
    }

}
