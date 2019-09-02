package cn.blocks.commondisruptor.common;

import cn.blocks.commonutils.Utils.LogUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lmax.disruptor.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @description
 *          disruptor 异常处理
 *
 * @auther Somnus丶y
 * @date 2019/9/2 9:58
 */
@Slf4j
public class DisruptorExceptionHandler implements ExceptionHandler {

    @Override
    public void handleEventException(Throwable ex, long sequence, Object event) {
        LogUtils.error(log,ex,"disruptor数据异常sequence:%s,event:%s",
                sequence, JSON.toJSONString(event, SerializerFeature.DisableCircularReferenceDetect));
    }

    @Override
    public void handleOnStartException(Throwable ex) {
        LogUtils.error(ex,"disruptor启动异常");
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
        LogUtils.error(ex,"disruptor关闭异常");
    }

}
