package com.axkea.publish.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/3 19:57
 */
@Data
public class VideoCardDTO {

    private String likeCount;
    private String videoTitle;
    private Long userId;
    private Date createTime;
    private String videoUrl;
    private Long videoId;

}
