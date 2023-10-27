package com.axkea.review.core.examine.api;

import com.alibaba.fastjson.JSONObject;
import com.axkea.review.common.http.msg.MsgBuilder;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author Genius
 * @date 2023/10/26 20:39
 **/
public abstract class AbstractApiBuilder implements ApiBuilder{


    @Override
    public ApiBuilder body(MsgBuilder builder) {
        return body(builder.done());
    }

}
