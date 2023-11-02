package com.axkea.like.domain.vo;

import lombok.Data;

/**
 * @Author Axkea
 * @Date 2023/10/27/027 0:31
 * @Description
 */
@Data
public class LikeActionVo {
    private  Boolean actionType;
    private  Long videoId;
    private  Long authorId;
}
