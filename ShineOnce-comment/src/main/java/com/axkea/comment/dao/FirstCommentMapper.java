package com.axkea.comment.dao;

import com.axkea.comment.pojo.FirstLevelComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/27 10:49
 */
@Mapper
public interface FirstCommentMapper extends BaseMapper<FirstLevelComment> {
}
