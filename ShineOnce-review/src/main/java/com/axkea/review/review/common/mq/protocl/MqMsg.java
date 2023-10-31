package com.axkea.review.review.common.mq.protocl;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author welsir
 * @date 2023/10/26 23:34
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MqMsg<T> {

    private long msgId;
    private T body;
    private Class<T> type;

    public MqMsg(long msgId, T body) {
        this.msgId = msgId;
        this.body = body;
        this.type = (Class<T>) body.getClass();
    }

    public String toString(){
        return JSONObject.toJSONString(this);
    }

    public static <T> MqMsg<T> toMqMsg(String json){
        MqMsg<T> mqMsg = JSONObject.parseObject(json, MqMsg.class);
        if (mqMsg.getBody() instanceof JSONObject) {
            Object data = JSONObject.parseObject(((JSONObject) mqMsg.getBody()).toJSONString(), mqMsg.getType());
            T body = mqMsg.getType().cast(data);
            mqMsg.setBody(body);
        }
        return mqMsg;
    }

}
