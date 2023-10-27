package com.axkea.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author Axkea
 * @Date 2023/10/25/025 16:28
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User {
    private static final long serialVersionUID = 2023L;

    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String username;

    private String password;

    private String avatar;

    private String signature;

    private boolean enabled = true;

    private boolean activation = false;

    private int age ;

    private boolean gender = false;

    private String email;

    private Date createdAt;

    private Date updateAt;
}
