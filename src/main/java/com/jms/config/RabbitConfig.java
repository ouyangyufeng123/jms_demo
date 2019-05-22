package com.jms.config;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author ouyangyufeng
 * @date 2019/5/21
 */
@Configuration
public class RabbitConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;


    public static final String EXCHANGE_A = "my-mq-exchange_A";
    public static final String EXCHANGE_B = "my-mq-exchange_B";
    public static final String EXCHANGE_C = "my-mq-exchange_C";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";


    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }


    /**
     * -----------------------------交换机配置----------------------------------------
     * DirectExchange
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchangeA() {
        return new DirectExchange(EXCHANGE_A);
    }

    @Bean
    public DirectExchange defaultExchangeB() {
        return new DirectExchange(EXCHANGE_B);
    }

    @Bean
    public DirectExchange defaultExchangeC() {
        return new DirectExchange(EXCHANGE_C);
    }


    /**
     * FanoutExchange
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }


    /**
     * -----------------------------消息队列配置----------------------------------------
     * 获取队列
     * @return
     */
    @Bean
    public Queue queueA() {
        //队列持久
        return new Queue(QUEUE_A, true);
    }

    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_B, true);
    }

    @Bean
    public Queue queueC() {
        return new Queue(QUEUE_C, true);
    }


    /**
     * -----------------------------消息绑定配置----------------------------------------
     * 消息绑定到DirectExchange交换机上去
     * @return
     */
    @Bean
    public Binding bindingA() {
        return BindingBuilder.bind(queueA()).to(defaultExchangeA()).with(RabbitConfig.ROUTINGKEY_A);
    }

    @Bean
    public Binding bindingB(){
        return BindingBuilder.bind(queueB()).to(defaultExchangeB()).with(RabbitConfig.ROUTINGKEY_B);
    }

    @Bean
    public Binding bindingC(){
        return BindingBuilder.bind(queueC()).to(defaultExchangeC()).with(RabbitConfig.ROUTINGKEY_C);
    }


    /**
     * 把所有的队列都绑定到FanoutExchange交换机上去
     * @return
     */
    @Bean
    public Binding bindingExchangeA() {
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingExchangeB() {
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingExchangeC() {
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }

}
