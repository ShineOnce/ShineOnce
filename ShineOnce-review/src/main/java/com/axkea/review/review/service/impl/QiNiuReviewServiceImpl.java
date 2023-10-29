package com.axkea.review.review.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.axkea.review.common.ExamineEvent;
import com.axkea.review.common.RedisKey;
import com.axkea.review.common.logger.CommonLogger;
import com.axkea.review.config.RedisKeyConfig;
import com.axkea.review.service.ReviewService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author welsir
 * @date 2023/10/27 19:51
 **/
@Service("qiniuReviewService")
public class QiNiuReviewServiceImpl implements ReviewService {

    @Resource
    private CommonLogger logger;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void VideoExamineCallback(JSONObject resp) {
        String vid = resp.getString("vid");
        RedisKey redisKey = RedisKeyConfig.VIDEO_EXAMINE_CALLBACK;
        logger.info("七牛云视频审核vid = %s回调执行 ",vid);
        Optional.ofNullable(stringRedisTemplate.opsForValue().getAndDelete(redisKey.getKey(vid)))
                .ifPresent((data)->{
                    logger.info("七牛云视频审核vid = %s回调执行 回调数据 = %s",vid,data);
                    ExamineEvent event = JSONObject.parseObject(data, ExamineEvent.class);
                    String res = Optional.ofNullable(resp.getJSONObject("result")
                            .getJSONObject("result")
                            .getString("suggestion")).orElse("");
                    if(res.equals("pass")||res.equals("review")){
                        logger.info("执行审核通过url"+event.getSuccessUrl());
                    }else{
                        logger.info("执行审核不通过url"+event.getFailUrl());
                    }
                });
    }
}
