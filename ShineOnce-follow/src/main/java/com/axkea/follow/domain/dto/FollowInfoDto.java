package com.axkea.follow.domain.dto;

import lombok.Data;

/**
 * @Author Axkea
 * @Date 2023/10/27 16:57
 * @Description
 */
@Data
public class FollowInfoDto {
    private Long followNumber;
    private Long followerNumber;
    private Long friendNumber;
    private Boolean followStatus;//表示是否关注 true是 false否
}
