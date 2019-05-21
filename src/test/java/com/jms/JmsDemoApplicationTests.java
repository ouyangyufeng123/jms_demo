package com.jms;

import com.jms.activemq.ActivemqProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsDemoApplicationTests {

    @Autowired
    private ActivemqProducer producer;

    @Test
    public void sendActivemqMessage() {
        Destination destination = new ActiveMQQueue("mytest.queue");

        for (int i = 0; i < 100; i++) {
            producer.sendMessage(destination, "myname is chhliu!!!");
        }


    }

}
