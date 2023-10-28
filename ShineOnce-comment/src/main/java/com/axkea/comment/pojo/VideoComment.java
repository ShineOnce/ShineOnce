package com.axkea.comment.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/27 17:10
 */
@Data
public class VideoComment {

    private String firstLevelComment;
    private String name;

    private List<SLComment> slComments;

}
