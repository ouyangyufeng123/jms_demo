package com.jms.rabbitmq;

import com.jms.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 *
 * @author ouyangyufeng
 * @date 2019/5/21
 */
@Component
public class RabbitmqProducer implements RabbitTemplate.ConfirmCallback {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * 由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
     */
    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public RabbitmqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
        rabbitTemplate.setConfirmCallback(this);
    }


    /**
     * 发送消息
     * @param content
     */
    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_A, content, correlationId);
    }

    public void sendAll(String content) {
        rabbitTemplate.convertAndSend("fanout_exchange","", content);
    }

    /**
     * 回调
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(@Nullable CorrelationData correlationData, boolean ack, @Nullable String cause) {
        LOGGER.info(" 回调id:" + correlationData);
        if (ack) {
            LOGGER.info("消息成功消费");
        } else {
            LOGGER.info("消息消费失败:" + cause);
        }
    }
}
