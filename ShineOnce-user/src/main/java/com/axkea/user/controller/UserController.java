package com.axkea.user.controller;

import com.axkea.common.api.Result;
import com.axkea.user.domain.User;
import com.axkea.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Axkea
 * @Date 2023/10/25/025 16:22
 * @Description 用户查询的接口
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getInfo")
    public Result<User> getInfo(String uid){
        return Result.success(userService.getInfo(uid));
    }

    @GetMapping("/usernameExist")
    public Result<Boolean> usernameExist(String username){
        if (userService.getInfoByUsername(username)==null){
            return Result.success(false);
        }
        return Result.success(true);
    }
}
