package be.civadis.gpdoc.amqp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public abstract class AMQPAbstractListener {

    protected RabbitTemplate rabbitTemplate;
    protected final String retryQueueName;
    protected final String retryRoutingKey;
    
    public AMQPAbstractListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.retryQueueName = null;
        this.retryRoutingKey = null;
    }

    public AMQPAbstractListener(RabbitTemplate rabbitTemplate, String retryQueueName, String retryRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.retryQueueName = retryQueueName;
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
            System.out.println("Received content :");
            System.out.println(content);
            return new ObjectMapper().readValue(content, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AmqpRejectAndDontRequeueException("Error while deserializing object message");
        }
    }


    // TODO revoir trt retry, error, ... existe params spring boot

    /**
     *
     * @param message objet du model à transmettre
     * @param type
     */
    protected void retryMessage(Object model, String type){
        rabbitTemplate.convertAndSend(retryQueueName, retryRoutingKey, model, m -> {
            m.getMessageProperties().setType(type);
            m.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return m;
        });
    }

    protected void fallbackMessage(Message object, Throwable t){
        String eventType = getType(object);
        System.out.println("FALLBACK on event : " + eventType);
        this.retryMessage(object, eventType);
        //si exception dans fallback, elle est transmise a rabbitMQ
    }

}