package com.jms.rabbitmq;

import com.jms.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息队列负载均衡
 * @author ouyangyufeng
 * @date 2019/5/21
 */
@Component
@RabbitListener(queues = {RabbitConfig.QUEUE_A})
public class RabbitmqTwoConsumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String content) {
        logger.info("接收处理队列Two当中的消息： " + content);
    }

}
