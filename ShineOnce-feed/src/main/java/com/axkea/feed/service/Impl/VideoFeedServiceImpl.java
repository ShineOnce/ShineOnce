package com.axkea.feed.service.Impl;

import com.axkea.comment.pojo.VideoComment;
import com.axkea.common.pojo.User;
import com.axkea.common.pojo.Video;
import com.axkea.feed.client.*;
import com.axkea.feed.mapper.VideoFeedMapper;
import com.axkea.feed.pojo.vo.VideoFeedVO;
import com.axkea.feed.pojo.vo.VideoRecommendVO;
import com.axkea.feed.service.VideoFeedService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/4 23:34
 */
@Component
public class VideoFeedServiceImpl extends ServiceImpl<VideoFeedMapper,Video> implements VideoFeedService {

    @Resource
    VideoFeedMapper videoFeedMapper;
    @Resource
    UserClient userClient;
    @Resource
    CommentClient commentClient;
    @Resource
    FollowClient followClient;
    @Resource
    LikeClient likeClient;
    @Resource
    CollectionClient collectionClient;

    @Override
    public List<List<VideoFeedVO>> getVideoCardByPage() {

        List<Video> videoList = videoFeedMapper.selectList(null);
        ArrayList<List<VideoFeedVO>> list = new ArrayList<>();
        ArrayList<VideoFeedVO> arrayList = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            if(i%5==0){
                list.add(arrayList);
                arrayList = new ArrayList<>();
            }
            User user = userClient.getUser(videoList.get(i).getUserId().toString());
            VideoFeedVO build = VideoFeedVO.builder()
                    .videoId(videoList.get(i).getId())
                    .userId(videoList.get(i).getUserId())
                    .videoUrl(videoList.get(i).getVideoUrl())
                    .title(videoList.get(i).getTitle())
                    .createTime(videoList.get(i).getCreateTime())
                    .username(user.getUsername())
                    .likesCount(videoList.get(i).getLikeCount())
                    .build();
            arrayList.add(build);
        }
        return list;
    }

    @Override
    public VideoRecommendVO getRecommend(String userId) {
        List<Video> videoList = videoFeedMapper.selectList(null);
        Random random = new Random();
        int i = random.nextInt(videoList.size());
        Video video = videoList.get(i);
        User user = userClient.getUser(video.getUserId().toString());
        List<VideoComment> comment = commentClient.getCommentByVId(video.getId().toString());
        Boolean like = likeClient.isLike(video.getId().toString(), userId);
        Boolean follow = followClient.checkRelated(userId, video.getId().toString());
        Boolean collection = collectionClient.isCollection(video.getId().toString(), userId);
        return VideoRecommendVO.builder()
                .userId(video.getUserId())
                .videoId(video.getId())
                .videoUrl(video.getVideoUrl())
                .collectionCount(video.getCollectionCount())
                .createTime(video.getCreateTime())
                .likesCount(video.getLikeCount())
                .userPic(user.getAvatar())
                .commentCount(comment)
                .isFollow(follow)
                .isLikes(like)
                .isCollection(collection)
                .title(video.getTitle())
                .username(user.getUsername())
                .build();
    }

    @Override
    public List<VideoFeedVO> getListByTag(String tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("tag",tag);
        List<Video> videoList = videoFeedMapper.selectByMap(map);
        ArrayList<VideoFeedVO> videoFeedVOS = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            User user = userClient.getUser(videoList.get(i).getUserId().toString());
            VideoFeedVO build = VideoFeedVO.builder()
                    .videoId(videoList.get(i).getId())
                    .userId(videoList.get(i).getUserId())
                    .videoUrl(videoList.get(i).getVideoUrl())
                    .title(videoList.get(i).getTitle())
                    .createTime(videoList.get(i).getCreateTime())
                    .username(user.getUsername())
                    .likesCount(videoList.get(i).getLikeCount())
                    .build();
            videoFeedVOS.add(build);
        }
        return videoFeedVOS;
    }

}
