package cn.blocks.commonamqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static cn.blocks.commonamqp.constant.ExchangeConstant.TEST_TOPIC;
import static cn.blocks.commonamqp.constant.QueueConstant.QUEUE_TEST;

/**
 * @description
 * @author Somnus丶y
 * @date 2019/9/28 11:46
 */
//@EnableRabbit
@Configuration
@ConditionalOnProperty(value = "blocks.rabbit.enable",havingValue = "true")
public class RabbitmqConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "blocks.rabbit")
    public RabbitProperties rabbitProperties(){
        return new RabbitProperties();
    }


    @Bean
    public MessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }




    @Bean
    public Queue topicQueue1() {
        return new Queue(QUEUE_TEST);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TEST_TOPIC);
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("test.test.*");
    }


}
