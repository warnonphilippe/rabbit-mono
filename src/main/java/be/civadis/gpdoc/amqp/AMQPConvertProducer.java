package be.civadis.gpdoc.amqp;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
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
        System.out.println("Send message : " + tc.toString());

        rabbitTemplate.convertAndSend(AMQPConvertConfig.CONVERT_EXCHANGE_NAME, AMQPConvertConfig.CONVERT_ROUTING_KEY, tc , 
            new CustomAbstractMessagePostProcessor() {
                @Override
                public Message addAdditionnalInfos(Message message) throws AmqpException {
                    message.getMessageProperties().setType(TicketConversionDto.class.getName());
                    return message;
                }
            });
    }

    
}