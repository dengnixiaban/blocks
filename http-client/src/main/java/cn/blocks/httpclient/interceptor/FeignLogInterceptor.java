package cn.blocks.httpclient.interceptor;

import cn.blocks.commonutils.utils.LogUtils;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

/**
 * @description
 *          日志拦截  ->传递
 *
 * @auther Somnus丶y
 * @date 2019/9/12 17:08
 */
@Order(value = 0)
@Slf4j
public class FeignLogInterceptor implements FeignClientInterceptor{

    @Override
    public void apply(RequestTemplate template) {
        String logId = LogUtils.getLogId();
        if(StringUtils.isEmpty(logId)){
            LogUtils.initLogId();
            LogUtils.info("client remote时无logid,手动生成一个");
            logId = LogUtils.getLogId();
        }
        template.header(LogUtils.LOGID,logId);
    }

    @Override
    public boolean match(RequestTemplate template) {
        return true;
    }


}
