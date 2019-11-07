package cn.blocks.scheduleservice.listener;

import cn.blocks.commonamqp.constant.AmqpConstant;
import cn.blocks.scheduleservice.model.event.MqEvent;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.ListenerContainerIdleEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @description
 *             RabbitListener在方法上，消息接收之前会在MessageProperties中加上method和inferredArgumentType
 *             无需typeId一致
 *
 *         在class上则需要一致或者自定义classmapper
 *
 *
 * @auther Somnus丶y
 * @date 2019/9/28 11:59
 */

@Component
public class TestListener {



    @RabbitListener(id = "foo",queues = AmqpConstant.QUEUE_TEST)
    @RabbitHandler
    public String consumeTest(@Payload MqEvent event, @Headers Map<String,Object> headers,
                              Channel channel) throws IOException {
        System.out.println("mq消费:" + JSON.toJSONString(event));
        if(true){
            throw new RuntimeException();
        }
        channel.basicAck(1000L,false);
        return "success";
    }


    @EventListener(condition = "event.listenerId == 'foo'")
    public void onApplicationEvent(ListenerContainerIdleEvent event) {
        System.out.println(11);
    }


}
