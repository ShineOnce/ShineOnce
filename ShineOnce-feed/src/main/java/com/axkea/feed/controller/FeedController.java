package com.axkea.feed.controller;

import com.axkea.common.api.Result;
import com.axkea.feed.mapper.VideoFeedMapper;
import com.axkea.feed.pojo.vo.VideoFeedVO;
import com.axkea.feed.pojo.vo.VideoRecommendVO;
import com.axkea.feed.service.VideoFeedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/4 23:12
 */
@RestController
@RequestMapping("feed")
public class FeedController {

    @Resource
    VideoFeedService videoFeedService;

    @GetMapping("/cards")
    public Result<List<List<VideoFeedVO>>> getCards(){
        return Result.success(videoFeedService.getVideoCardByPage());
    }

    @GetMapping("/recommend/{userId}")
    public Result<VideoRecommendVO> getRecommendVideo(@PathVariable String userId){
        VideoRecommendVO recommend = videoFeedService.getRecommend(userId);
        return Result.success(recommend);
    }

    @GetMapping("/recommend/{tag}")
    public Result<List<VideoFeedVO>> getVideoListByTag(@PathVariable String tag){
        return Result.success(videoFeedService.getListByTag(tag));
    }

}
