package com.axkea.review.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author Genius
 * @date 2023/10/26 21:41
 **/
//TODO 可以被提取到common中
@Data
@AllArgsConstructor
public class RedisKey {
    private String key;
    private Long time;
    private TimeUnit unit;

    public RedisKey(String key) {
        this.key = key;
        this.time = -1L;
        this.unit = TimeUnit.SECONDS;
    }

    public String getKey(Object...keys){
        return String.format(key, keys);
    }
}
