package cn.blocks.scheduleservice.notify;

import cn.blocks.scheduleservice.model.event.NotifyMsg;

import java.util.concurrent.ExecutionException;

/**
 * @description
 *          通知策略
 *
 * @auther Somnus丶y
 * @date 2019/10/17 10:55
 */
public interface NotifyStrategy {

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
    void notify(NotifyMsg notifyMsg) throws ExecutionException, InterruptedException;

}
