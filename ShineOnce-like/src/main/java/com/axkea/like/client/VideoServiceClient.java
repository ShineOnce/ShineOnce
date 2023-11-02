package com.axkea.like.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author Axkea
 * @Date 2023/11/2/002 14:18
 * @Description
 */
@Service
@FeignClient(value = "FeedApplication")
public interface VideoServiceClient {
//    @GetMapping("/feed/getInfo/{id}")
//    User queryUserById(@PathVariable String id);
}
