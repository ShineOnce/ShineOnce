package com.axkea.feed.pojo.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/5 0:33
 */
@Data
@Builder
public class VideoFeedVO {

    private Long videoId;
    private Long userId;
    private String username;
    private String title;
    private String videoUrl;
    private Date createTime;
    private String likesCount;

}
