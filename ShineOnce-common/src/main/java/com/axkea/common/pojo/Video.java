package com.axkea.common.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/1 22:01
 */
@Data
@Builder
public class Video {

    private Long id;
    private Long userId;
    private String title;
    private String description;
    @TableField("file_url")
    private String videoUrl;
    private String coverUrl;
    private String collectionCount;
    private String likeCount;
    private Date createAt;
    private Date updateAt;
    private String tagId;
    private boolean isReview;

}
