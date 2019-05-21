package com.jms.rabbitmq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 *
 * @author ouyangyufeng
 * @date 2019/5/21
 */
@Component
public class RabbitmqProducer implements RabbitTemplate.ConfirmCallback{




    @Override
    public void confirm(@Nullable CorrelationData correlationData, boolean b, @Nullable String s) {

    }
}
