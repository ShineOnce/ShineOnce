package com.axkea.publish.core.controller;

import com.axkea.common.api.Result;
import com.axkea.publish.core.factory.AbstractSDKFactory;
import com.axkea.publish.core.service.VideoService;
import com.axkea.common.pojo.Video;
import com.axkea.publish.pojo.dto.VideoCardDTO;
import com.axkea.publish.pojo.vo.VideoUploadVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    VideoService videoService;


    @PostMapping("/upload")
    public Result uploadVideo(VideoUploadVO videoUploadVO){
        try {
            return sdkFactory.uploadFile(videoUploadVO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{userid}")
    public Result<List<Video>> getVideoListByUserId(@PathVariable String userid){
        return Result.success(videoService.getVideoByUserId(userid));
    }

    @GetMapping("/videoCard")
    public Result<VideoCardDTO> getVideoCard(){
        return null;
    }

}
