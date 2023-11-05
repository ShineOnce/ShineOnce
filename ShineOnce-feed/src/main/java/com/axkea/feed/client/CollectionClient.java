package com.axkea.feed.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/5 1:21
 */
@FeignClient(name = "CollectionApplication")
public interface CollectionClient {

    @GetMapping("collection/isCollection")
    Boolean isCollection(@RequestParam("videoId") String videoId,@RequestParam("userId") String userId);
}
