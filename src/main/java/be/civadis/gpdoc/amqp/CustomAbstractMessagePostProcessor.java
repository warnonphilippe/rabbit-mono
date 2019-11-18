package be.civadis.gpdoc.amqp;

import be.civadis.gpdoc.multitenancy.TenantContext;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * AbstractMessagePostProcessor
 */
public abstract class CustomAbstractMessagePostProcessor implements MessagePostProcessor {

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        message.getMessageProperties().setHeader("tenant", TenantContext.getCurrentTenant());
        message.getMessageProperties().setHeader("application", TenantContext.getCurrentApp());
        return addAdditionnalInfos(message);
    }

    public abstract Message addAdditionnalInfos(Message message) throws AmqpException;
    
}
