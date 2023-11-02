package com.axkea.follow.service;

import com.axkea.follow.domain.Follow;
import com.axkea.follow.domain.dto.FollowInfoDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author Axkea
 * @Date 2023/10/27 16:39
 * @Description
 */

public interface FollowService extends IService<Follow> {
    Boolean addFollow(Long userId,Long toUserId);
    Boolean deleteFollow(Long userId,Long toUserId);
    List getFollowUserList(Long toUserId);
    List getFollowerUserList(Long toUserId);
    List getFriendUserList(Long toUserId);
    FollowInfoDto getFollowInfo(Long userId,Long toUserId);

}
