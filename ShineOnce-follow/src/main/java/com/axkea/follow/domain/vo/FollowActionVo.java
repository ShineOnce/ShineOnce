package com.axkea.follow.domain.vo;

import lombok.Data;

/**
 * @Author Axkea
 * @Date 2023/10/27 16:28
 * @Description
 */
@Data
public class FollowActionVo {
    private Boolean actionType;
    private Long toUserId;
}
