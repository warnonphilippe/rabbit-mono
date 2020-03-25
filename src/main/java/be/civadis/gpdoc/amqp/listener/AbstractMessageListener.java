package be.civadis.gpdoc.amqp.listener;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import be.civadis.gpdoc.amqp.common.AbstractMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public abstract class AbstractMessageListener extends AbstractMessageService {

    private static final Logger log = LoggerFactory.getLogger(AbstractMessageListener.class);

    protected final String retryExchangeName;
    protected final String retryRoutingKey;

    public AbstractMessageListener(RabbitTemplate rabbitTemplate) {
        this(rabbitTemplate, null, null);
    }

    public AbstractMessageListener(RabbitTemplate rabbitTemplate, String retryExchangeName, String retryRoutingKey) {
        super(rabbitTemplate);
        this.retryExchangeName = retryExchangeName;
        this.retryRoutingKey = retryRoutingKey;
    }

    /**
     * Extrait le content du message
     * @param message
     * @return
     */
    public String getContent(Message message){
        return new String(message.getBody(), StandardCharsets.UTF_8);
    }

    /**
     * Extrait le content du message et le converti en object de type T
     * @param message
     * @return
     */
    public <T> T getContent(Message message, Class<T> clazz) {
        String content = this.getContent(message);
        return getModel(content, clazz);
    }

    /**
     * Extrait le type du message
     * @param message
     * @return
     */
    public String getType(Message message){
        return message.getMessageProperties().getType();
    }

    /**
     * Convertit un content JSON en object
     * @param content
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T getModel(String content, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(content, clazz);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new AmqpRejectAndDontRequeueException("Error while deserializing object message");
        }
    }


    // TODO revoir trt retry, error, ... existe params spring boot

    /**
     *
     * @param model objet du model à transmettre
     */
    protected void retryMessage(Object model){
        if (retryExchangeName == null || StringUtils.isBlank(retryExchangeName) || retryRoutingKey == null || StringUtils.isBlank(retryRoutingKey)) {
            throw new IllegalArgumentException("RetryQueueName && RetryRoutingKey doivent être défini");
        }
        this.convertAndSend(retryExchangeName, retryRoutingKey, model);
    }

    protected void fallbackMessage(Message object){
        this.retryMessage(object);
        //si exception dans fallback, elle est transmise a rabbitMQ
    }

}
