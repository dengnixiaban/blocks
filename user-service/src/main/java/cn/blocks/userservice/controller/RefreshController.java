package cn.blocks.userservice.controller;

import cn.blocks.httpserver.annotation.AccessLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *          配置刷新服务
 *
 * 当有请求/fresh节点的时候，会重新请求一次ConfigServer去拉取最新的配置文件
 * 请求/fresh需要有几点要求：1.加actuator的依赖 2.SpringCloud1.5以上需要设置 management.security.enabled=false
 * 这个Controller的作用是查看from这个key的值
 *
 * @author Somnus丶y
 * @date 2019/8/20 14:30
 */
@RestController
@RefreshScope //开启更新功能
@RequestMapping("api")
public class RefreshController {

    @Value("${test.dev}")
    private String fromValue;

    /**
     * 返回配置文件中的值
     */
    @AccessLog(desc = "/api/form")
    @GetMapping("/from")
    @ResponseBody
    public String returnFormValue() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        return fromValue;
    }

}
