package com.axkea.feed.client;

import com.axkea.comment.pojo.VideoComment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/5 1:11
 */
@FeignClient(name = "CommentApplication")
public interface CommentClient {

    @GetMapping("comment/getComment")
    List<VideoComment> getCommentByVId(@RequestParam("videoId") String id);


}
