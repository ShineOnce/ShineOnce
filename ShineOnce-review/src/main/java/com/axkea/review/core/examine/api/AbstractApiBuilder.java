package com.axkea.review.core.examine.api;

import com.axkea.review.common.http.msg.MsgBuilder;

/**
 * @author welsir
 * @date 2023/10/26 20:39
 **/
public abstract class AbstractApiBuilder implements ApiBuilder{


    @Override
    public ApiBuilder body(MsgBuilder builder) {
        return body(builder.done());
    }

}
