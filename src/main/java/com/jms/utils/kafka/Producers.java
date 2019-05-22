package com.jms.utils.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

/**
 *  消息生产者
 * @author ouyangyufeng
 * @date 2019/2/18
 */
public class Producers {

    public static Producer<String, String> getProducer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        // 设置分区器
        props.put("partitioner.class", "com.yl.battery.manage.kafka.MyPartition");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new KafkaProducer<>(props);
    }

}
