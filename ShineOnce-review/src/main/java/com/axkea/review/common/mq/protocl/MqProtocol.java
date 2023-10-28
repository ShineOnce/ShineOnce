package com.axkea.review.common.mq.protocl;


import org.springframework.amqp.core.Message;

public interface MqProtocol<T> {
    Message encode(T decodeData);

    T decode(Message encodeData);
}
