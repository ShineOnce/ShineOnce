package com.axkea.user.domain.dto;

import com.axkea.user.domain.User;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author Axkea
 * @Date 2023/10/25/025 21:25
 * @Description
 */
@Data
@Accessors(chain = true)
public class LoginDto {
    private String token;
    private User user;
}
