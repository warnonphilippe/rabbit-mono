package be.civadis.gpdoc.amqp;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.civadis.gpdoc.dto.TicketConversionDto;

/**
 * Producer
 */
@Service
public class AMQPConvertProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTicketConversion(TicketConversionDto tc) throws Exception {
        rabbitTemplate.convertAndSend(AMQPConvertConfig.CONVERT_QUEUE_NAME, AMQPConvertConfig.CONVERT_ROUTING_KEY, m -> {
            m.getMessageProperties().setType(TicketConversionDto.class.getName());
            m.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return m;
        });
    }

    
}