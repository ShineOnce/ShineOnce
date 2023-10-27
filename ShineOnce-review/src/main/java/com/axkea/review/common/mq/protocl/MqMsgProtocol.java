package com.axkea.review.common.mq.protocl;

import com.alibaba.fastjson.JSONObject;
import com.axkea.review.util.random.NumberRandomMachine;
import com.axkea.review.util.random.RandomFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author Genius
 * @date 2023/10/27 00:02
 **/
@Component
public class MqMsgProtocol<T> implements MqProtocol<MqMsg<T>>{

    @Override
    public Message encode(MqMsg<T> decodeData) {
        byte[] messageBodyBytes = JSONObject.toJSONString(decodeData).getBytes();
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDeliveryTag(decodeData.getMsgId());
        return new Message(messageBodyBytes, messageProperties);
    }

    @Override
    public MqMsg<T> decode(Message message) {
        String data = new String(message.getBody());
        return MqMsg.toMqMsg(data);
    }
}
