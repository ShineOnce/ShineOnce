package com.axkea.review.mq.listener;

import com.axkea.review.config.MqConst;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Genius
 * @date 2023/10/26 19:28
 **/
@Component
public class ReviewListener {

    @Resource
    ReviewConsumer consumer;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MqConst.AUDIO_QUEUE),
            exchange = @Exchange(name = MqConst.SHINEONCE_DIRECT_EXCHANGE, type = ExchangeTypes.DIRECT),
            key = {"audio"}
    ),ackMode = "MANUAL")
    public void listenAudioQueue(Message message, Channel channel){
        consumer.consume(message,channel,"audio");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MqConst.VIDEO_QUEUE),
            exchange = @Exchange(name = MqConst.SHINEONCE_DIRECT_EXCHANGE, type = ExchangeTypes.DIRECT),
            key = {"video"}
    ),ackMode = "MANUAL")
    public void listenVideoQueue(Message message, Channel channel){
        consumer.consume(message,channel,"video");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MqConst.IMG_QUEUE),
            exchange = @Exchange(name = MqConst.SHINEONCE_DIRECT_EXCHANGE, type = ExchangeTypes.DIRECT),
            key = {"image"}
    ),ackMode = "MANUAL")
    public void listenImgQueue(Message message, Channel channel){
        consumer.consume(message,channel,"image");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MqConst.TEXT_QUEUE),
            exchange = @Exchange(name = MqConst.SHINEONCE_DIRECT_EXCHANGE, type = ExchangeTypes.DIRECT),
            key = {"text"}
    ),ackMode = "MANUAL")
    public void listenTextQueue(Message message, Channel channel){
        consumer.consume(message,channel,"text");
    }
}
