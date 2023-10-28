package com.axkea.review.mq.config;


import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Genius
 * @date 2023/10/27 21:04
 **/
@Configuration
public class RabbitConfig {

    @Bean
    public SimpleRabbitListenerContainerFactory myRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置手动确认模式
        factory.setAfterReceivePostProcessors(message -> {
            // 执行消费者确认后的回调操作
            System.out.println("Message received: " + message);
            return message;
        });
        return factory;
    }
}
