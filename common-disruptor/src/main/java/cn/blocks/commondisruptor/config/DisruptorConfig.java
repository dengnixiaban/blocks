package cn.blocks.commondisruptor.config;

import cn.blocks.commondisruptor.common.DisruptorExceptionHandler;
import cn.blocks.commondisruptor.disruptor.event.UserEvent;
import cn.blocks.commondisruptor.disruptor.handler.UserEventHandler;
import cn.blocks.commondisruptor.disruptor.transfer.UserEventTransfer;
import cn.blocks.commonutils.model.BlocksThreadFactory;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @description
 *          disruptor配置
 *
 * @author Somnus丶y
 * @date 2019/9/24 15:51
 */
@Configuration
public class DisruptorConfig {

    /**
     * 用户处理线程池大小
     */
    @Value("${blocks.disruptor.userevent.size:10}")
    private int userEventSize;

    /**
     * ring大小
     */
    private static final int RING_BUFFER_SIZE = 2 << 15;


    @Bean
    public DisruptorExceptionHandler disruptorExceptionHandler(){
        return new DisruptorExceptionHandler();
    }



    /******************************************userevent-begin***********************************************/


    @Bean
    @Scope(value = "prototype")
    public UserEventHandler userEventHandler(){
        return new UserEventHandler();
    }

    @Bean
    public UserEventTransfer userEventTransfer(){
        return new UserEventTransfer();
    }



    @Bean
    public Disruptor userEventDisruptor(DisruptorExceptionHandler disruptorExceptionHandler){
        Disruptor userEventDisruptor = new Disruptor<UserEvent>(
                ()->new UserEvent(),
                RING_BUFFER_SIZE,
                new BlocksThreadFactory("userEvent_disruptor-"),
                ProducerType.MULTI,
                new BlockingWaitStrategy());
        userEventDisruptor.setDefaultExceptionHandler(disruptorExceptionHandler);
        UserEventHandler[] groupMemberDataHandlers = new UserEventHandler[userEventSize];
        for (int i = 0; i < userEventSize; i++) {
            groupMemberDataHandlers[i] = userEventHandler();
        }
        //        userEventDisruptor.handleEventsWith(groupMemberDataHandlers);
        userEventDisruptor.handleEventsWithWorkerPool(groupMemberDataHandlers);
        userEventDisruptor.start();
        return userEventDisruptor;
    }



    /******************************************userevent-end***********************************************/


}
