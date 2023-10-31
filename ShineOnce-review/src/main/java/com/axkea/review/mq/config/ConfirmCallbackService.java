package com.axkea.review.mq.config;

import com.axkea.review.common.logger.CommonLogger;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Genius
 * @date 2023/10/27 03:06
 **/
@Component
public class ConfirmCallbackService implements RabbitTemplate.ConfirmCallback {
    @Resource
    private CommonLogger logger;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        if (!ack) {
            logger.error("消息发送异常!");
        } else {
            logger.info("发送者已经收到确认，ack=%s, cause=%s",true, s);
        }
    }
}
