package com.axkea.review.common.http.msg;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/10/26 20:51
 **/
public class QiNiuMsgBuilder extends AbstractMsgBuilder {

    private final Map<String, Object> params = new HashMap<>();
    private final Map<String,Object> data = new HashMap<>();

    public QiNiuMsgBuilder params(String key,Object value) {
        params.put(key, value);
        return this;
    }

    public QiNiuMsgBuilder data(String key,Object value) {
        data.put(key,value);
        return this;
    }

    @Override
    public String done() {
        this.build("params",params).build("data",data);
        return super.done();
    }
}
