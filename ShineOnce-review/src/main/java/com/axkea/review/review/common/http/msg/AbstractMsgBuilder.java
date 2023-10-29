package com.axkea.review.review.common.http.msg;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author welsir
 * @date 2023/10/26 20:42
 **/
public class AbstractMsgBuilder implements MsgBuilder{

    private Map<String,Object> data;


    public AbstractMsgBuilder() {
        this.data = new HashMap<>();
    }

    @Override
    public MsgBuilder build(String key, Object value) {
        data.put(key,value);
        return this;
    }

    @Override
    public String done() {
        return JSON.toJSONString(data);
    }
}
