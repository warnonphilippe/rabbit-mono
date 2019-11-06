package be.civadis.gpdoc.amqp;

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
        // TODO récupérer tenant et application du contexte
        message.getMessageProperties().setHeader("tenant", "jhipster");
        message.getMessageProperties().setHeader("application", "testapp");
        return addAdditionnalInfos(message);
    }

    public abstract Message addAdditionnalInfos(Message message) throws AmqpException;
    
}