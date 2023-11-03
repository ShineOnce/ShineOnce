package com.axkea.follow.controller;

import com.axkea.common.api.Result;
import com.axkea.follow.domain.vo.FollowActionVo;
import com.axkea.follow.service.FollowService;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Axkea
 * @Date 2023/10/27 16:25
 * @Description
 */
@RestController
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    FollowService followService;
    @PostMapping("/followAction")
    public Result<Boolean> followAction(@RequestHeader Long userId,@RequestBody FollowActionVo vo){
        //todo 目标用户的校验
        if (vo.getActionType())return Result.success(followService.addFollow(userId,vo.getToUserId()));
        return Result.success(followService.deleteFollow(userId,vo.getToUserId()));
    }
    @GetMapping("/getFollowList")
    public Result getFollowList(@RequestBody Long toUserId){
        return Result.success(followService.getFollowUserList(toUserId));
    }
    @GetMapping("/getFollowerList")
    public Result getFollowerList(@RequestBody Long toUserId){
        return Result.success(followService.getFollowerUserList(toUserId));
    }
    @GetMapping("/getFriendList")
    public Result getFriendList(@RequestBody Long toUserId){
        return Result.success(followService.getFriendUserList(toUserId));
    }
    @GetMapping("/getFollowInfo")
    public Result getFollowInfo(@RequestHeader Long userId,@RequestBody Long toUserId){
        return Result.success(followService.getFollowInfo(userId,toUserId));
    }

}
