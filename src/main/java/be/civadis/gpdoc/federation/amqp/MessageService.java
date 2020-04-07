package be.civadis.gpdoc.federation.amqp;

import be.civadis.gpdoc.config.amqp.AmqpConvertQueuesBizConfiguration;
import be.civadis.gpdoc.config.amqp.AmqpEboxQueuesBizConfiguration;
import be.civadis.gpdoc.domain.TicketEnvoiEbox;
import be.civadis.gpdoc.federation.amqp.dto.ConversionMessageDTO;
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

    /**
     * Envoyer un message à RabbitMQ contenant un TicketEnvoiEbox,ce ticket sera traité en asynchrone par un listener dès que le message lui aura été remi
     * @param envoi conv ticket contenant les infos de l'envoi ebox demandé
     */
    public void envoyerMessageEnvoi(TicketEnvoiEbox envoi) {
        this.convertAndSend(AmqpEboxQueuesBizConfiguration.ENVOI_EBOX_EXCHANGE_NAME, AmqpEboxQueuesBizConfiguration.ENVOI_EBOX_ROUTING_KEY, envoi);
    }

}