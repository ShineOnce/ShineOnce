package com.axkea.follow.service.imp;

import com.axkea.follow.dao.FollowDao;
import com.axkea.follow.domain.Follow;
import com.axkea.follow.domain.dto.FollowInfoDto;
import com.axkea.follow.service.FollowService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author Axkea
 * @Date 2023/10/27 16:41
 * @Description
 */
public class FollowServiceImp extends ServiceImpl<FollowDao, Follow> implements FollowService{
    //redis关注列表标志key
    public final static  String FOLLOW_LIST_KEY = ":follow";
    //redis粉丝列表标志key
    public final static  String FOLLOWER_LIST_KEY = ":follower";
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private  FollowDao followDao;
    @Override
    public Boolean addFollow(Long userId, Long toUserId) {
        //判断是否已经关注目标用户
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).eq("to_user_id",toUserId);
        if(getOne(wrapper)!=null){
            return false;
        }
        //插入数据
        Follow follow = new Follow().setUserId(userId).setToUserId(toUserId);
        if (!save(follow)) {
            return false;
        }
        //更新redis，将目标用户加入登录用户的followSet中，并将登录用户加入目标用户的followerSet中
        redisTemplate.opsForSet().add(userId+FOLLOW_LIST_KEY,toUserId);
        redisTemplate.opsForSet().add(toUserId+FOLLOWER_LIST_KEY,userId);
        return true;
    }

    @Override
    public Boolean deleteFollow(Long userId, Long toUserId) {
        //删除数据
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).eq("to_user_id",toUserId);
        if(!remove(wrapper)) return false;
        //更新redis,将目标用户从登录用户的followSet中移除，并将登录用户从目标用户的followerSet中移除
        redisTemplate.opsForSet().remove(userId+FOLLOW_LIST_KEY,toUserId);
        redisTemplate.opsForSet().remove(toUserId+FOLLOWER_LIST_KEY,userId);
        return true;
    }

    @Override
    //传入进来的是你实际要看的用户id
    public List getFollowUserList(Long toUserId) {
        String followKey = toUserId + FOLLOW_LIST_KEY;
        List result = new ArrayList();
        //先按 userId 和 “:follow” 查redis
        if (redisTemplate.opsForSet().size(followKey) != 0){
            result.addAll(redisTemplate.opsForSet().members(followKey));
            return result;
        }
        //查数据库，更新redis
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",toUserId);
         result = followDao.selectList(wrapper);
        updateRedis(followKey,result);
        return result;
    }

    @Override
    //传入进来的是你实际要看的用户id
    public List getFollowerUserList(Long toUserId) {
        String followerKey = toUserId + FOLLOWER_LIST_KEY;
        List result = new ArrayList();
        //先按 toUserId 和 “:follower” 查redis
        if (redisTemplate.opsForSet().size(followerKey) != 0){
            result.addAll(redisTemplate.opsForSet().members(followerKey));
            return result;
        }
        //查数据库，更新redis
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("to_user_id",toUserId);
        result = followDao.selectList(wrapper);
        updateRedis(followerKey,result);
        return result;
    }


    @Override
    //传入的值应该是你要查询的用户的id
    public List getFriendUserList(Long toUserId) {
        String followKey = toUserId + FOLLOW_LIST_KEY;
        String followerKey = toUserId + FOLLOWER_LIST_KEY;
        List result = new ArrayList();

        //先从redis中查 关注和粉丝表的交集 ，查不到就查数据库，并更新redis，然后查redis查他们的交集
        if (redisTemplate.opsForSet().size(followKey) == 0){
            getFollowUserList(toUserId);
        }
        if (redisTemplate.opsForSet().size(followerKey) == 0){
            getFollowerUserList(toUserId);
        }
        result.addAll(redisTemplate.opsForSet().intersect(followKey,followerKey));
        return result;
    }

    @Override
    public FollowInfoDto getFollowInfo(Long userId, Long toUserId) {
        String followKey = toUserId + FOLLOW_LIST_KEY;
        String followerKey = toUserId + FOLLOWER_LIST_KEY;
        //从redis中找followNumber，followerNumber，friendNumber(这个需要getFriendList)，follow（true表示关注，false表示没有关注）
            FollowInfoDto followInfoDto = new FollowInfoDto();
            if (redisTemplate.opsForSet().size(followKey)!=0){
                followInfoDto.setFollowNumber(redisTemplate.opsForSet().size(followKey));
            }
            else {
                getFollowUserList(userId);
                followInfoDto.setFollowNumber(redisTemplate.opsForSet().size(followKey));
            }
            if(redisTemplate.opsForSet().size(followerKey)!=0){
                followInfoDto.setFollowerNumber(redisTemplate.opsForSet().size(followerKey));
            }
            else {
                getFollowUserList(toUserId);
                followInfoDto.setFollowerNumber(redisTemplate.opsForSet().size(followerKey));
            }
            followInfoDto.setFriendNumber((long) getFriendUserList(toUserId).size());
            followInfoDto.setFollowStatus(redisTemplate.opsForSet().isMember(followerKey,userId));

        return followInfoDto;
    }

    /**
     * 用来更新redis的
     * @param key 用来确定redis中的set的
     * @param val 要加入到set中的用户id列表
     */
    public void updateRedis(String key,List val){
        Iterator iterator = val.iterator();
        while (iterator.hasNext()){
            redisTemplate.opsForSet().add(key,iterator.next());
        }
    }
}
