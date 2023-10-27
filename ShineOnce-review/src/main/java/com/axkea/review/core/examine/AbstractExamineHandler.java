package com.axkea.review.core.examine;

import com.axkea.review.common.ExamineEvent;
import com.axkea.review.common.RedisKey;
import com.axkea.review.config.RedisKeyConfig;
import com.rabbitmq.client.Channel;
import lombok.Data;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author Genius
 * @date 2023/10/26 23:20
 **/
@Data
public abstract class AbstractExamineHandler implements ExamineHandler{

    private Channel channel;

    private long deliverTag;

    private long msgId;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean examine(ExamineEvent context, String type) {
        switch (type) {
            case "audio":
                return examineAudio(context);
            case "video":
                return examineVideo(context);
            case "image":
                return examineImg(context);
            case "text":
                return examineContent(context);
            default:
                return false;
        }
    }



    public void fail(){

    }
}
