package com.axkea.like.domain.dto;

import lombok.Data;

/**
 * @Author Axkea
 * @Date 2023/10/27/027 1:26
 * @Description
 */
@Data
public class UserLikeInfoDto {
    private Long toLikeNumber;//点赞数
    private Long forLikeNumber;//获赞数
}
