package cn.blocks.userservice;

import cn.blocks.commondisruptor.disruptor.event.UserEvent;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *      disruptor test
 *
 * @author Somnus丶y
 * @date 2019/9/24 18:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DisruptorTests {

    @Autowired
    private Disruptor<UserEvent> userEventDisruptor;

    @Autowired
    private EventTranslatorOneArg<UserEvent,UserEvent> userEventTransfer;


    @Test
    public void test1() throws InterruptedException {
        RingBuffer<UserEvent> userEventRingBuffer = userEventDisruptor.getRingBuffer();
        long seq = userEventRingBuffer.next();
        try
        {
            UserEvent userEvent = userEventRingBuffer.get(seq);
            userEvent.setName("aaa");
        }
        finally
        {
            userEventRingBuffer.publish(seq);
        }
        TimeUnit.SECONDS.sleep(2);
    }


}
