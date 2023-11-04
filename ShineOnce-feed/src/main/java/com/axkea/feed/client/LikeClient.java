package com.axkea.feed.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/5 1:20
 */
@FeignClient(name = "LikeApplication")
public interface LikeClient {

    @GetMapping("Like/isLike")
    Boolean isLike(@RequestParam("videoId") String videoId,@RequestParam("userId") String userId);
}
