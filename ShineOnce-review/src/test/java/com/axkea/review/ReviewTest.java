package com.axkea.review;

import com.axkea.review.common.ExamineEvent;
import com.axkea.review.common.mq.protocl.MqMsg;
import com.axkea.review.common.mq.protocl.MqProtocol;
import com.axkea.review.mq.config.ConfirmCallbackService;
import com.axkea.review.util.random.NumberRandomMachine;
import com.axkea.review.util.random.RandomFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ReviewTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    ConfirmCallbackService confirmCallbackService;

    @Resource
    MqProtocol mqProtocol;

    @Test
    public void testSendDirectExchange() throws InterruptedException {
        // 交换机名称

        // 发送消息
        for (int i = 0; i < 1; i++) {
            String exchangeName = "shineonce.direct";
            // 消息
            ExamineEvent build = ExamineEvent.builder().content("原来是这样的").url("https://timemachinelab.oss-cn-hangzhou.aliyuncs.com/11%3Ayuan.png").build();
            MqMsg<ExamineEvent> mqMsg = MqMsg.<ExamineEvent>builder()
                    .msgId(RandomFactory.RandomMachine(NumberRandomMachine.class).random())
                    .body(build)
                    .type(ExamineEvent.class).build();

            Message encode = mqProtocol.encode(mqMsg);
            rabbitTemplate.setConfirmCallback(confirmCallbackService);
            rabbitTemplate.convertAndSend(exchangeName, "img", encode);
            rabbitTemplate.invoke(operations->{
                System.out.println(operations);
                System.out.println("回调");
                return null;
            });
        }
        Thread.sleep(5000);
    }
}
