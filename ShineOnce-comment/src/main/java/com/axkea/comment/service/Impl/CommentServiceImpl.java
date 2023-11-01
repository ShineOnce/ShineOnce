package com.axkea.comment.service.Impl;

import com.axkea.comment.client.UserClientService;
import com.axkea.comment.dao.FirstCommentMapper;
import com.axkea.comment.dao.SecondCommentMapper;
import com.axkea.comment.pojo.FirstLevelComment;
import com.axkea.comment.pojo.SLComment;
import com.axkea.comment.pojo.SecondLevelComment;
import com.axkea.comment.pojo.VideoComment;
import com.axkea.comment.pojo.vo.FirstLevelCommentVO;
import com.axkea.comment.pojo.vo.SecondLevelCommentVO;
import com.axkea.comment.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/27 10:48
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    FirstCommentMapper firstCommentMapper;
    @Resource
    SecondCommentMapper secondCommentMapper;
    @Resource
    UserClientService service;

    @Override
    public FirstLevelComment addFLComment(FirstLevelCommentVO firstLevelCommentVO) {
        FirstLevelComment firstLevelComment = FirstLevelComment.copyProperties(firstLevelCommentVO);
        firstCommentMapper.insert(firstLevelComment);
        return firstLevelComment;
    }

    @Override
    public SecondLevelComment addSLComment(SecondLevelCommentVO secondLevelCommentVO) {
        SecondLevelComment secondLevelComment = SecondLevelComment.copyProperties(secondLevelCommentVO);
        secondCommentMapper.insert(secondLevelComment);
        return secondLevelComment;
    }

    @Override
    public void removeFLComment(Long commentId) {
        firstCommentMapper.deleteById(commentId);
    }

    @Override
    public void removeSLComment(Long commentId, Long parentId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",commentId);
        map.put("parent_id",parentId);
        secondCommentMapper.deleteByMap(map);
    }

    @Override
    public List<VideoComment> getCommentByVId(Long videoId) {
        QueryWrapper<FirstLevelComment> wrapper = new QueryWrapper<>();
        ArrayList<VideoComment> videoComments = new ArrayList<>();
        wrapper.eq("video_id",videoId);
        List<FirstLevelComment> firstLevelComments = firstCommentMapper.selectList(wrapper);
        for (FirstLevelComment firstLevelComment : firstLevelComments) {
            VideoComment videoComment = new VideoComment();
            videoComment.setName(service.queryUserById(String.valueOf(firstLevelComment.getCommentId())).getUsername());
            List<SecondLevelComment> sonComments = secondCommentMapper.selectList(new QueryWrapper<SecondLevelComment>().eq("parent_id", firstLevelComment.getId()));
            ArrayList<SLComment> slComments = new ArrayList<>();
            for (SecondLevelComment sonComment : sonComments) {
                SLComment slComment = new SLComment();
                slComment.setParentId(firstLevelComment.getId());
                slComment.setFromName(service.queryUserById(sonComment.getFromUid().toString()).getUsername());
                slComment.setToName(service.queryUserById(sonComment.getToUid().toString()).getUsername());
                slComments.add(slComment);
            }
            videoComment.setSlComments(slComments);
            videoComment.setName(service.queryUserById(firstLevelComment.getCommentId().toString()).getUsername());
            videoComments.add(videoComment);
        }
        return videoComments;
    }
}
