package com.axkea.publish.controller;

import com.axkea.common.api.Result;
import com.axkea.publish.factory.AbstractSDKFactory;
import com.axkea.publish.pojo.VideoUpload;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/1 11:16
 */
@RestController
@RequestMapping("video")
public class VideoController {

    @Resource
    AbstractSDKFactory sdkFactory;

    @PostMapping("/upload/{userId}")
    public Result uploadVideo(@RequestParam("file") MultipartFile multipartFile, @PathVariable String userId){
        try {
            File file = new File("D://1.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileCopyUtils.copy(multipartFile.getBytes(), file);
            return sdkFactory.uploadFile(file,userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
