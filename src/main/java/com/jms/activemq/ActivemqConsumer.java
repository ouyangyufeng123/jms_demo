package com.jms.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author ouyangyufeng
 * @date 2019/5/21
 */
@Component
public class ActivemqConsumer {

    int count = 50;

    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     *
     * @param text: 收到的消息信息
     * @return
     */
    @JmsListener(destination = "mytest.queue")
    @SendTo("out.queue")
    public String receiveQueue(String text) {
        if (count >= 0) {
            System.out.println("已秒杀" + text);
        } else {
            System.out.println("活动结束!");
        }
        count -= 1;
        return text;
    }


    @JmsListener(destination = "mytest1")
    public void receiveQueue2(String text) {
        System.out.println("Consumer收到的报文为:" + text);
    }

}
