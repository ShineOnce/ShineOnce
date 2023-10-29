package com.axkea.review.controller;

import com.alibaba.fastjson.JSONObject;
import com.axkea.review.service.ReviewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author welsir
 * @date 2023/10/27 20:05
 **/
@RestController("/review")
public class ReviewController {

    @Resource(name = "${review.examine.handler}ReviewService")
    private ReviewService reviewService;

    @PostMapping("/video/callback")
    public void review(@RequestBody JSONObject data){
        reviewService.VideoExamineCallback(data);
    }
}
