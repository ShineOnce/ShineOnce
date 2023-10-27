package com.axkea.review.config;

import com.axkea.review.common.RedisKey;
import com.baomidou.mybatisplus.extension.api.R;

import java.util.concurrent.TimeUnit;

/**
 * @author Genius
 * @date 2023/10/26 21:40
 **/
public class RedisKeyConfig {
    public static final RedisKey RABBIT_TAG = new RedisKey("rabbit_tag",5L, TimeUnit.MINUTES);

    public static final RedisKey VIDEO_EXAMINE_CALLBACK = new RedisKey("video_tag:%s",-1L, TimeUnit.MINUTES);
}
