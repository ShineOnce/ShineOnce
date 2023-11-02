package com.axkea.publish.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/1 11:18
 */
@Data
public class VideoUpload {

    private String videoPath;
    private String userId;
    private Date uploadTime;
}
