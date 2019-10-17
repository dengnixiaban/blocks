package cn.blocks.scheduleservice.notify.strategy;

import cn.blocks.scheduleservice.model.event.NotifyMsg;
import cn.blocks.scheduleservice.notify.NotifyStrategy;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * @description
 *          rabbit通知策略
 *
 * @auther Somnus丶y
 * @date 2019/10/17 10:56
 */
@Component
public class RabbitNotifyStrategy implements NotifyStrategy {


    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ExecutorService scheExecutors;


    /**
     * @description
     *          调度通知
     *
     * @param notifyMsg
     *          消息体
     *
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/10/17
     */
    @Override
    public void notify(NotifyMsg notifyMsg){
        scheExecutors.submit(() -> {
            Object message = amqpTemplate.convertSendAndReceive(notifyMsg.getExchange(),notifyMsg.getRoutKey()
                    ,notifyMsg.getMsg());
            return message;
        });
    }


}
