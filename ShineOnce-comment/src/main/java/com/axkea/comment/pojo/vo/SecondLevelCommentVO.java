package com.axkea.comment.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/28 21:52
 */
@Data
public class SecondLevelCommentVO {
    private Long parentId;
    private Long formUid;
    private Long toUid;
    private String replyType;
    private String content;
}
