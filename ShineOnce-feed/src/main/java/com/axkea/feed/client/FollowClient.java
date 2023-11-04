package com.axkea.feed.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/5 1:17
 */
@FeignClient(name = "FollowApplication")
public interface FollowClient {

    @GetMapping("follow/checkRelated")
    Boolean checkRelated(@RequestParam("userId") String userId,@RequestParam("videoId") String videoId);
}
