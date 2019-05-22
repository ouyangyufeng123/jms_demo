package com.jms.kafka;

import com.jms.utils.kafka.KafkaListenerUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 * @author ouyangyufeng
 * @date 2019/5/21
 */
@Component
public class KafkaConsumer {

    /**
     * 消费消息
     */
//    @KafkaListener(topics = {"test01"})
    public void consumeMessage(ConsumerRecord<?, ?> record){
        Optional<?> kafkaMessage = KafkaListenerUtil.MessageListener(record);
        if (kafkaMessage.isPresent()) {
            System.out.println(record.value());
        }
    }

}
