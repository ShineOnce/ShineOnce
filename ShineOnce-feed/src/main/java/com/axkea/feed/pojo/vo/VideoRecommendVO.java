package com.axkea.feed.pojo.vo;

import com.axkea.comment.pojo.VideoComment;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/5 0:52
 */
@Data
@Builder
public class VideoRecommendVO {
    private Long videoId;
    private Long userId;
    private String username;
    private String title;
    private String videoUrl;
    private Date createTime;
    private String likesCount;
    private List<VideoComment> commentCount;
    private String collectionCount;
    private String userPic;
    private boolean isFollow;
    private boolean isLikes;
    private boolean isCollection;
}
