package com.axkea.user.service.impl;


import com.axkea.common.api.Result;
import com.axkea.common.util.JwtUtils;
import com.axkea.user.component.SendMess;
import com.axkea.user.dao.UserDao;
import com.axkea.user.domain.User;
import com.axkea.user.domain.dto.LoginDto;
import com.axkea.user.service.UserService;
import com.axkea.user.util.MD5Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author Axkea
 * @Date 2023/10/25/025 16:31
 * @Description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    SendMess sendMess;

    @Override
    public Result<String> register(User user) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("email",user.getEmail()).or().eq("username",user.getUsername());
        if(getOne(wrapper)!=null){
            return Result.failed("邮箱或用户名已被注册");
        } else {
            user.setCreatedAt(new Date());
            user.setPassword(MD5Utils.stringToMD5(user.getPassword()));
            sendMess.SendActivation(user,user.getEmail());
            save(user);
            return Result.success("注册成功");
        }
    }

    @Override
    public Result<LoginDto> login(User user) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        String email= user.getEmail();
        if (email==null){
            wrapper.eq("username",user.getUsername());
        }else{
            wrapper.eq("email",user.getEmail());
        }
        User one = getOne(wrapper);
        if (one==null)return Result.failed("用户不存在");
        else if(!one.isActivation())return Result.failed("用户未激活");
        else if(MD5Utils.stringToMD5(user.getPassword()).equals(one.getPassword())){
            String token = JwtUtils.getJwtToken(one.getId(),one.getUsername());
            one.setPassword(null);
            return Result.success(new LoginDto().setToken(token).setUser(one));
        }else {
            return Result.failed("密码错误");
        }
    }

    @Override
    public User getInfo(String id) {
        User user = getById(id);
        user.setPassword(null);
        return user;
    }

    @Override
    public String activation(String token) {
        try {
            String name = JwtUtils.getMemberNameByJwtToken(token);
            UpdateWrapper<User> wrapper = new UpdateWrapper<>();
            wrapper.eq("username",name).set("activation",1);
            update(wrapper);
            return "账号激活成功";
        }catch (Exception e){
            System.out.println(e);
            return "账号激活失败 失败信息为".concat(e.getMessage());
        }
    }

    @Override
    public User getInfoByUsername(String username) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        return getOne(wrapper);
    }
}
