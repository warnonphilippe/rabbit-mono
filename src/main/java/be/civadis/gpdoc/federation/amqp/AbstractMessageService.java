package be.civadis.gpdoc.federation.amqp;

import be.civadis.gpdoc.multitenancy.TenantContext;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class AbstractMessageService {

    protected RabbitTemplate rabbitTemplate;

    public AbstractMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    protected <T> void convertAndSend(String exchangeName, String routingKey, T data) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, data,
            message -> {
                message.getMessageProperties().setType(data.getClass().getName());
                return message;
            });
    }

}
