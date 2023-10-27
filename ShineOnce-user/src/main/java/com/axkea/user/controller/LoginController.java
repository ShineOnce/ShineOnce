package com.axkea.user.controller;

import com.axkea.common.api.Result;
import com.axkea.user.domain.User;
import com.axkea.user.domain.vo.LoginVo;
import com.axkea.user.domain.vo.RegisterVo;
import com.axkea.user.service.UserService;
import com.axkea.user.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Axkea
 * @Date 2023/10/25/025 16:20
 * @Description 登录，注册的web接口
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result<String> Register(@RequestBody RegisterVo vo){
        String email=vo.getEmail();
        String password=vo.getPassword();
        if (!CheckUtil.isEmail(email)){
            return Result.failed("邮箱格式有误");
        }
        if (!CheckUtil.checkPassword(password)){
            return Result.failed("密码长度应为8-20");
        }
        User user=new User().setEmail(email).setPassword(password).setUsername(vo.getUsername());
        return userService.register(user);
    }

    @GetMapping("/activation")
    public String getActivation(String token){
        return userService.activation(token);
    }

    @PostMapping("/login")
    public Result Login(@RequestBody LoginVo vo){
        String account=vo.getAccount();
        String password=vo.getPassword();
        if (!CheckUtil.checkPassword(password)){
            return Result.failed("密码长度应为8-20");
        }
        User user=new User().setPassword(password);
        if (CheckUtil.isEmail(account)){
            user.setEmail(account);
        }else{
            user.setUsername(account);
        }
        return userService.login(user);
    }

}
