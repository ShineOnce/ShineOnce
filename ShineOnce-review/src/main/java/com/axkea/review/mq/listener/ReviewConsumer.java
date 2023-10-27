package com.axkea.review.mq.listener;

import com.axkea.review.common.ExamineEvent;
import com.axkea.review.common.mq.protocl.MqMsg;
import com.axkea.review.common.mq.protocl.MqMsgProtocol;
import com.axkea.review.core.examine.AbstractExamineHandler;
import com.axkea.review.common.logger.CommonLogger;
import com.axkea.review.common.RedisKey;
import com.axkea.review.config.RedisKeyConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Genius
 * @date 2023/10/26 21:40
 **/
@Component
public class ReviewConsumer {

    @Resource
    CommonLogger logger;

    @Resource
    private MqMsgProtocol<ExamineEvent> mqProtocol;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    AbstractExamineHandler examineHandler;

    public void consume(Message message, Channel channel,String type){
        RedisKey redisKey = RedisKeyConfig.RABBIT_TAG;
        String key = redisKey.getKey();
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        MqMsg<ExamineEvent> decode = mqProtocol.decode(message);
        long msgId = decode.getMsgId();
        logger.info("【消息%s】 正在接收处理...",msgId);
        try {
            if(Boolean.TRUE.equals(stringRedisTemplate.opsForHash().putIfAbsent(key,Long.toString(msgId),String.valueOf(Boolean.FALSE)))){
                if (examineHandler.examine(decode.getBody(),type)) {
                    channel.basicAck(deliveryTag,false);
                    logger.info("【消息%s】【类型%s】审核成功",msgId,type);
                }else{
                    channel.basicNack(deliveryTag, true, false);
                    logger.info("【消息%s】【类型%s】审核失败",msgId,type);
                }
                stringRedisTemplate.opsForHash().put(key,Long.toString(msgId),String.valueOf(Boolean.TRUE));
                stringRedisTemplate.expire(key,redisKey.getTime(),redisKey.getUnit());
            }else{
                if(Boolean.TRUE.equals(Boolean.getBoolean((String) stringRedisTemplate.opsForHash().get(key,Long.toString(msgId))))){
                    logger.info("【消息%s】已存在，不再重复消费", msgId);
                    channel.basicAck(deliveryTag,false);
                }
                channel.basicNack(deliveryTag, true, false);
                logger.error("【消息%s】消费失败",msgId);
            }
        }catch (Exception e){
            try {
                channel.basicNack(deliveryTag, true, false);
                stringRedisTemplate.opsForHash().put(key,Long.toString(deliveryTag),String.valueOf(Boolean.TRUE));
                stringRedisTemplate.expire(key,redisKey.getTime(),redisKey.getUnit());
                logger.error("【消息%s】消费失败，原因:%s",msgId,e.getMessage());
            }catch (Exception ex) {
                logger.error("错误！【消息%s】丢失，原因:%s",msgId,ex.getMessage());
            }
        }
    }

}
