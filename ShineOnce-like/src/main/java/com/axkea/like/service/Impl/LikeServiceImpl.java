package com.axkea.like.service.Impl;

import com.axkea.common.api.Result;
import com.axkea.like.dao.LikeDao;
import com.axkea.like.domain.Like;
import com.axkea.like.domain.dto.UserLikeInfoDto;
import com.axkea.like.domain.dto.VideoLikeInfoDto;
import com.axkea.like.service.LikeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Axkea
 * @Date 2023/10/26/026 20:55
 * @Description
 */
@Service
public class LikeServiceImpl extends ServiceImpl<LikeDao, Like> implements LikeService {
    public final static  String VIDEO_LIKE_COUNT_KEY = ":videoLikeCount";
    public final static  String USER_LIKE_COUNT_KEY = ":userLikeCount";
    public final static  String ALL_GET_LIKE_COUNT_KEY = ":AllGetLikeCount";//用户总的视频的获赞数
    @Autowired
    private  LikeDao likeDao;
    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public Boolean addLike(Long videoId, Long userId, Long authorId) {
        //判断是否已经点赞
        QueryWrapper<Like> wrapper = new QueryWrapper();
        wrapper.eq("video_id",videoId).eq("user_id",userId);
        if(getOne(wrapper)!=null){
            return false;
        }
        Like like = new Like().setUserId(userId).setVideoId(videoId).setAuthorId(authorId);
        if(!save(like)){
            return false;
        }
        //更新redis（点赞后的影响）
        if(redisTemplate.opsForHash().hasKey(videoId,VIDEO_LIKE_COUNT_KEY)){
            redisTemplate.opsForHash().increment(videoId,VIDEO_LIKE_COUNT_KEY,1);
        }
        if(redisTemplate.opsForHash().hasKey(userId,USER_LIKE_COUNT_KEY)){
            redisTemplate.opsForHash().increment(userId,USER_LIKE_COUNT_KEY,1);
        }
        if (redisTemplate.opsForHash().hasKey(authorId,ALL_GET_LIKE_COUNT_KEY)){
            redisTemplate.opsForHash().increment(authorId,ALL_GET_LIKE_COUNT_KEY,1);
        }
        return true;
    }

    @Override
    public Boolean deleteLike(Long videoId, Long userId, Long authorId) {
        QueryWrapper<Like> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).eq("video_id",videoId);
         if(!remove(wrapper)) return false;
        //更新redis（取消点赞后的影响）
        if(redisTemplate.opsForHash().hasKey(videoId,VIDEO_LIKE_COUNT_KEY)){
            redisTemplate.opsForHash().increment(videoId,VIDEO_LIKE_COUNT_KEY,1);
        }
        if(redisTemplate.opsForHash().hasKey(userId,USER_LIKE_COUNT_KEY)){
            redisTemplate.opsForHash().increment(userId,USER_LIKE_COUNT_KEY,1);
        }
        if (redisTemplate.opsForHash().hasKey(authorId,ALL_GET_LIKE_COUNT_KEY)){
            redisTemplate.opsForHash().increment(authorId,ALL_GET_LIKE_COUNT_KEY,1);
        }
        return true;
    }

    @Override
    public VideoLikeInfoDto getAllLikeVideoIdByUserId(Long userId) {
        QueryWrapper<Like> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Long> videoIdList = likeDao.getLikeVideoIdListByUserID(userId);
        //todo 调用下游的video模块获取video的相关信息并返回
        return null;
    }

    @Override
    public VideoLikeInfoDto queryLikeInfo(Long userId, Long videoId) {
        VideoLikeInfoDto videoLikeInfoDto = new VideoLikeInfoDto();
        if (redisTemplate.opsForHash().hasKey(videoId,VIDEO_LIKE_COUNT_KEY)){
            videoLikeInfoDto.setLikeNumber((Long) redisTemplate.opsForHash().get(videoId,VIDEO_LIKE_COUNT_KEY));
            QueryWrapper<Like> wrapper = new QueryWrapper();
            wrapper.eq("user_id",userId).eq("video_id",videoId);
            //判断用户是否点赞
            if (getOne(wrapper)!=null)
            videoLikeInfoDto.setLike(true);
            else videoLikeInfoDto.setLike(false);
            return videoLikeInfoDto;
        }
        //根据videoId计算视频点赞量
        videoLikeInfoDto.setLikeNumber(likeDao.getVideoLikeNumberByVideoId(videoId));
        //判断用户是否点赞
        QueryWrapper<Like> wrapper = new QueryWrapper();
        wrapper.eq("user_id",userId).eq("video_id",videoId);
        //判断用户是否点赞
        if (getOne(wrapper)!=null)
            videoLikeInfoDto.setLike(true);
        else videoLikeInfoDto.setLike(false);
        return videoLikeInfoDto;
    }

    @Override
    public UserLikeInfoDto queryUserLikeInfo(Long userId) {
        UserLikeInfoDto userLikeInfoDto = new UserLikeInfoDto();
        //从redis查询用户点赞数
        if(redisTemplate.opsForHash().hasKey(userId,USER_LIKE_COUNT_KEY))
            userLikeInfoDto.setToLikeNumber((Long) redisTemplate.opsForHash().get(userId,USER_LIKE_COUNT_KEY));
        else {
            //从数据库查用户点赞数
            userLikeInfoDto.setToLikeNumber(likeDao.getToLikeNumber(userId));
        }

        //从redis查询作品获赞数
        if (redisTemplate.opsForHash().hasKey(userId,ALL_GET_LIKE_COUNT_KEY))
            userLikeInfoDto.setForLikeNumber((Long) redisTemplate.opsForHash().get(userId,ALL_GET_LIKE_COUNT_KEY));
        else {
            //从数据库查询作品获赞数
            userLikeInfoDto.setForLikeNumber(likeDao.getToLikeNumber(userId));
        }

        return userLikeInfoDto;
    }


}
