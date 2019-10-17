package cn.blocks.scheduleservice;

import cn.blocks.commonamqp.constant.ExchangeConstant;
import cn.blocks.scheduleservice.model.event.MqEvent1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleServiceApplicationTests {


    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void contextLoads() {
        MqEvent1 event = new MqEvent1("aa,","bb");
        amqpTemplate.convertAndSend(ExchangeConstant.TEST_TOPIC,"test.test.1", event);
    }



}
