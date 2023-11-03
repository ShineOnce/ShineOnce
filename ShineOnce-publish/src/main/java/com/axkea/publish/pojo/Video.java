package com.axkea.publish.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/1 22:01
 */
@Data
public class Video {

    private Long id;
    private Long userId;
    private String videoUrl;
    private String coverUrl;
    private String collectionCount;
    private String likeCount;
    private Date createTime;
    private Date updateTime;

}
