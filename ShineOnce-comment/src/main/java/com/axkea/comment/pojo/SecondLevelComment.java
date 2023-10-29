package com.axkea.comment.pojo;

import com.axkea.comment.pojo.vo.SecondLevelCommentVO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @Description 二级评论实体类
 * @Author welsir
 * @Date 2023/10/27 10:52
 */
@Data
@TableName(value = "reply")
public class SecondLevelComment {

    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private Long parentId;
    private Long fromUid;
    private Long toUid;
    private String replyType;
    private String content;
    private Date replyTime;
    private String likesCount;

    public static SecondLevelComment copyProperties(SecondLevelCommentVO secondLevelCommentVO){
        SecondLevelComment secondLevelComment = new SecondLevelComment();
        secondLevelComment.setFromUid(secondLevelComment.getFromUid());
        secondLevelComment.setToUid(secondLevelCommentVO.getToUid());
        secondLevelComment.setParentId(secondLevelCommentVO.getParentId());
        secondLevelComment.setContent(secondLevelCommentVO.getContent());
        secondLevelComment.setReplyType(secondLevelCommentVO.getReplyType());
        secondLevelComment.setReplyTime(new Date(System.currentTimeMillis()));
        return secondLevelComment;
    }

}
