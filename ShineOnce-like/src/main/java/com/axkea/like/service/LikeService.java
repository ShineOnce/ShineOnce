package com.axkea.like.service;

import com.axkea.common.api.Result;
import com.axkea.like.domain.Like;
import com.axkea.like.domain.dto.UserLikeInfoDto;
import com.axkea.like.domain.dto.VideoLikeInfoDto;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author Axkea
 * @Date 2023/10/26/026 20:54
 * @Description
 */
public interface LikeService extends IService<Like> {
    Boolean addLike(Long videoId,Long userId,Long authorId );
    Boolean deleteLike(Long videoId,Long userId,Long authorId );
    //根据用户id 查出所有的videoId ,没有封装成Dto，缺少信息，目的是返回video的相关信息
    Object getAllLikeVideoIdByUserId(Long userId);
    //查询视频的点赞星系已经当前用户是否点赞了
    VideoLikeInfoDto queryLikeInfo(Long userId,Long videoId);
    //查询用户给人点赞数以及用户作品点赞数
    UserLikeInfoDto queryUserLikeInfo(Long userId);

}
