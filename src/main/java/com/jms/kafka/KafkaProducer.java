package com.jms.kafka;

import com.jms.utils.kafka.KafkaSendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ouyangyufeng
 * @date 2019/5/21
 */
@Component
public class KafkaProducer {

    @Autowired
    private KafkaSendUtil kafkaSendUtil;

    /**
     * 发送消息
     */
    public void sendMessage(String message){
        kafkaSendUtil.send("test01", message);
    }

}
