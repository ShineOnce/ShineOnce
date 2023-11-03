package com.axkea.like.dao;

import com.axkea.like.domain.Like;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Axkea
 * @Date 2023/10/26/026 20:51
 * @Description
 */
@Repository
public interface LikeDao extends BaseMapper<Like> {
    List<Long> getLikeVideoIdListByUserID(Long userId);
    Long getVideoLikeNumberByVideoId(Long videoId);
    Long getToLikeNumber(Long userId);
    Long getForLikerNumber(Long authorId);
}
