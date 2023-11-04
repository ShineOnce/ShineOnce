package com.axkea.comment.controller;

import com.axkea.comment.pojo.FirstLevelComment;
import com.axkea.comment.pojo.SecondLevelComment;
import com.axkea.comment.pojo.VideoComment;
import com.axkea.comment.pojo.vo.FirstLevelCommentVO;
import com.axkea.comment.pojo.vo.SecondLevelCommentVO;
import com.axkea.comment.service.CommentService;
import com.axkea.common.api.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/10/27 10:41
 */

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    CommentService commentService;
    @PostMapping("/add/first")
    public Result<FirstLevelComment> addFirstLevelComment(FirstLevelCommentVO firstLevelComment){
        return Result.success(commentService.addFLComment(firstLevelComment));
    }

    @PostMapping("add/second")
    public Result<SecondLevelComment> addSecondLevelComment(SecondLevelCommentVO secondLevelComment){
        return Result.success(commentService.addSLComment(secondLevelComment));
    }

    @DeleteMapping("delete/first/{commentId}")
    public void removeFirstLevelComment(@PathVariable Long commentId){
        commentService.removeFLComment(commentId);
    }

    @DeleteMapping("delete/second/{parentId}/{commentId}")
    public void removeSecondLevelComment(@PathVariable Long commentId, @PathVariable Long parentId){
        commentService.removeSLComment(commentId,parentId);
    }

    @GetMapping("getComment/{videoId}")
    public Result<List<VideoComment>> getCommentByVId(@PathVariable String videoId){
        return Result.success(commentService.getCommentByVId(Long.parseLong(videoId)));
    }

}
