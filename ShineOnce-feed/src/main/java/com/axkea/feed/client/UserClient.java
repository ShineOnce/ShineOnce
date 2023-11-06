package com.axkea.feed.client;

import com.axkea.common.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/5 1:08
 */
@FeignClient(
        name = "UserApplication"
)
public interface UserClient {

    @GetMapping("/user/getInfo")
    User getUser(@RequestParam("uid") String id);
}
