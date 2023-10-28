package com.axkea.comment.client;

import com.axkea.user.domain.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/27 16:34
 */
@Component
@Service
@FeignClient(value = "UserApplication")
public interface UserClientService {

    @GetMapping("/user/getInfo/{id}")
    User queryUserById(@PathVariable String id);

}
