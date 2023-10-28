package com.axkea.comment.service;

import com.axkea.comment.pojo.FirstLevelComment;
import com.axkea.comment.pojo.SecondLevelComment;
import com.axkea.comment.pojo.VideoComment;
import com.axkea.comment.pojo.vo.FirstLevelCommentVO;
import com.axkea.comment.pojo.vo.SecondLevelCommentVO;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/27 10:48
 */
public interface CommentService {

    FirstLevelComment addFLComment(FirstLevelCommentVO firstLevelComment);

    SecondLevelComment addSLComment(SecondLevelCommentVO secondLevelComment);

    void removeFLComment(Long commentId);

    void removeSLComment(Long commentId,Long parentId);

    List<VideoComment> getCommentByVId(Long videoId);
}
