package cn.blocks.scheduleservice.listener;

import cn.blocks.commonamqp.constant.QueueConstant;
import cn.blocks.scheduleservice.model.MqEvent;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

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



    @RabbitListener(queues = QueueConstant.QUEUE_TEST)
    @RabbitHandler
    public void consumeTest(@Payload MqEvent event, @Headers Map<String,Object> headers){
        System.out.println("mq消费:" + JSON.toJSONString(event));
    }


}
