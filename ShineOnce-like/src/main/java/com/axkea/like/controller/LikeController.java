package com.axkea.like.controller;

import com.axkea.common.api.Result;
import com.axkea.like.domain.dto.UserLikeInfoDto;
import com.axkea.like.domain.dto.VideoLikeInfoDto;
import com.axkea.like.domain.vo.LikeActionVo;
import com.axkea.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Axkea
 * @Date 2023/10/26/026 20:50
 * @Description
 */
@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    LikeService likeService;
    @PostMapping("/likeAction")
    public Result<Boolean> likeAction(@RequestBody LikeActionVo vo, @RequestHeader Long userId ){
        if (vo.getActionType()){
            return Result.success(likeService.addLike(vo.getVideoId(),userId,vo.getAuthorId()));
        }
        return Result.success(likeService.deleteLike(vo.getVideoId(),userId,vo.getAuthorId()));
    }

    @GetMapping("/getLikeVideoList")
    public Result getLikeVideoList(@RequestHeader Long userId){
        //空的

        return null;
    }

    @GetMapping("/getVideoLikeInfo")
    public Result<VideoLikeInfoDto> getVideoLikeInfo(@RequestHeader Long userId, @RequestBody Long videoId){
        return Result.success(likeService.queryLikeInfo(userId, videoId));
    }

    @GetMapping("/getUserLikeInfo")
    public Result<UserLikeInfoDto> getUserLikeInfo(@RequestHeader Long userId){
        return Result.success(likeService.queryUserLikeInfo(userId));
    }

}
