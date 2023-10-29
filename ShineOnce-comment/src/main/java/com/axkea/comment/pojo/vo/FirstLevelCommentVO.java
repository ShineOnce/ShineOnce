package com.axkea.comment.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/28 21:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirstLevelCommentVO {
    private String videoId;
    private String commentId;
    private String content;
}
