package cn.blocks.commondisruptor.disruptor.transfer;

import cn.blocks.commondisruptor.disruptor.event.UserEvent;
import cn.blocks.commonutils.utils.LogUtils;
import com.lmax.disruptor.EventTranslatorOneArg;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description
 *          userEvent 转换
 *
 * @author Somnus丶y
 * @date 2019/9/24 17:43
 */
@Data
@Slf4j
public class UserEventTransfer implements EventTranslatorOneArg<UserEvent,UserEvent> {

    @Override
    public void translateTo(UserEvent event, long sequence, UserEvent arg0) {
        LogUtils.info(log,"userEvent转换-sequence=%s",sequence);

    }

}
