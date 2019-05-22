package com.jms.utils.kafka;

import org.springframework.stereotype.Component;

/**
 * 消息消费者
 * @author ouyangyufeng
 * @date 2019/2/18
 */
@Component
public class Consumers {

//    /**
//     * 消费主题名称
//     */
//    private static final String topicname = "test2";
//
//
//    @KafkaListener(id = "id0", topicPartitions = { @TopicPartition(topic = topicname, partitions = { "0" }) })
//    public void test(ConsumerRecord<?, ?> record) throws Exception{
//        System.out.println("id0消费消息");
//        KafkaListenerUtil.MessageListener(record);
//    }
//
//    @KafkaListener(id = "id1", topicPartitions = { @TopicPartition(topic = topicname, partitions = { "1" }) })
//    public void test1(ConsumerRecord<?, ?> record) throws Exception{
//        System.out.println("id1消费消息");
//        KafkaListenerUtil.MessageListener(record);
//    }
//
//    @KafkaListener(id = "id2", topicPartitions = { @TopicPartition(topic = topicname, partitions = { "2" }) })
//    public void test2(ConsumerRecord<?, ?> record) throws Exception{
//        System.out.println("id2消费消息");
//        KafkaListenerUtil.MessageListener(record);
//    }
}
