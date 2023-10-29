package com.axkea.review.core.examine.api;

import com.alibaba.fastjson.JSONObject;
import com.axkea.review.common.http.msg.MsgBuilder;
import com.qiniu.common.QiniuException;
import okhttp3.Request;

/**
 * @author Genius
 * @date 2023/10/26 20:35
 **/
public interface ApiBuilder {

    ApiBuilder header();

    ApiBuilder body(String body);

    ApiBuilder body(MsgBuilder builder);

    JSONObject post(String url) throws QiniuException;
    JSONObject get(String url);
}
