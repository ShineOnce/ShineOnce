package com.axkea.user.service;

import com.axkea.common.api.Result;
import com.axkea.user.domain.User;
import com.axkea.user.domain.dto.LoginDto;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author Axkea
 * @Date 2023/10/25/025 16:29
 * @Description
 */
public interface UserService extends IService<User> {
    Result<String> register(User user);

    Result<LoginDto> login(User user);

    User getInfo(String id);

    String activation(String token);

    User getInfoByUsername(String username);
}
