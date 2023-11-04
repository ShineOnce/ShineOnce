package com.axkea.feed.service;

import com.axkea.common.pojo.Video;
import com.axkea.feed.pojo.vo.VideoFeedVO;
import com.axkea.feed.pojo.vo.VideoRecommendVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/4 23:33
 */
public interface VideoFeedService extends IService<Video> {

    List<List<VideoFeedVO>> getVideoCardByPage();

    VideoRecommendVO getRecommend(String userId);

    List<VideoFeedVO> getListByTag(String tag);

}
