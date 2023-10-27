package com.axkea.review.mq.config;

import com.axkea.review.config.MqConst;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Genius
 * @date 2023/10/26 15:52
 **/
@Configuration
public class DirectExchangeConfig {


    @Bean
    public Queue videoQueue(){
        return new Queue(MqConst.VIDEO_QUEUE);
    }

    @Bean
    public Queue textQueue(){
        return new Queue(MqConst.TEXT_QUEUE);
    }

    @Bean
    public Queue imgQueue(){
        return new Queue(MqConst.IMG_QUEUE);
    }

    @Bean
    public Queue audioQueue(){
        return new Queue(MqConst.AUDIO_QUEUE);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MqConst.SHINEONCE_DIRECT_EXCHANGE);
    }

    @Bean
    public Binding bindingVideo(Queue videoQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(videoQueue).to(directExchange).with("video");
    }

    @Bean
    public Binding bindingImg(Queue imgQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(imgQueue).to(directExchange).with("img");
    }

    @Bean
    public Binding bindingAudio(Queue audioQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(audioQueue).to(directExchange).with("audio");
    }

    @Bean
    public Binding bindingContent(Queue textQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(textQueue).to(directExchange).with("text");
    }


}
