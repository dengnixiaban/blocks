package cn.blocks.commondisruptor.disruptor.handler;

import cn.blocks.commondisruptor.disruptor.event.UserEvent;
import cn.blocks.commonutils.utils.LogUtils;
import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @description
 *          userEvent 处理器
 *
 * @author Somnus丶y
 * @date 2019/9/24 17:46
 */
@Slf4j
public class UserEventHandler implements EventHandler<UserEvent>, WorkHandler<UserEvent> {

    /**
     * @description
     *          EventHandler
     *          并发处理
     *
     * @param event, sequence, endOfBatch
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/24
     */
    @Override
    public void onEvent(UserEvent event, long sequence, boolean endOfBatch) throws Exception {
        LogUtils.info(log,"UserEventHandler-EventHandler处理,event:%s", JSON.toJSONString(event));
    }


    /**
     * @description
     *          WorkHandler
     *          池处理
     *
     * @param event
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/24
     */
    @Override
    public void onEvent(UserEvent event) throws Exception {
        LogUtils.info(log,"UserEventHandler-WorkHandler处理,event:%s", JSON.toJSONString(event));
    }

}
