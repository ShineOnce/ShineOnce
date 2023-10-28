package com.axkea.comment.pojo;

import com.axkea.comment.pojo.vo.FirstLevelCommentVO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @Description 第一层评论实体类
 * @Author welsir
 * @Date 2023/10/27 10:42
 */
@Data
@TableName(value = "comment")
public class FirstLevelComment {

    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private Long videoId;
    @TableField("from_uid")
    private Long commentId;
    private String content;
    private Date commentTime;
    private String likesCount;

    public static FirstLevelComment copyProperties(FirstLevelCommentVO firstLevelCommentVO){
        FirstLevelComment firstLevelComment = new FirstLevelComment();
        firstLevelComment.setVideoId(Long.parseLong(firstLevelCommentVO.getVideoId()));
        firstLevelComment.setCommentTime(new Date(System.currentTimeMillis()));
        firstLevelComment.setContent(firstLevelCommentVO.getContent());
        firstLevelComment.setCommentId(Long.parseLong(firstLevelCommentVO.getCommentId()));
        return firstLevelComment;
    }
}
