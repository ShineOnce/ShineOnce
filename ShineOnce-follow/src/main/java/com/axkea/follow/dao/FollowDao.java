package com.axkea.follow.dao;

import com.axkea.follow.domain.Follow;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Axkea
 * @Date 2023/10/27 17:03
 * @Description
 */
@Repository
public interface FollowDao extends BaseMapper<Follow> {

}
