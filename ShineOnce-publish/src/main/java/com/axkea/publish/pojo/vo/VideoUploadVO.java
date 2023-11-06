package com.axkea.publish.pojo.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/1 11:18
 */
@Data
public class VideoUploadVO {
    private MultipartFile file;
    private String title;
    private String userId;
    private String tag;
    private String description;
}
