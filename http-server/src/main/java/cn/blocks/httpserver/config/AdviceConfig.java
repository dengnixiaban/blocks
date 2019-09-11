package cn.blocks.httpserver.config;

import cn.blocks.httpserver.controllerAdvice.HttpServerExceptionHandler;
import cn.blocks.httpserver.controllerAdvice.RespDataAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description
 *          mvc 统一控制config
 *
 * @author Somnus丶y
 * @date 2019/9/11 17:35
 */
@Configuration
public class AdviceConfig {


    /**
     * 异常处理
     * @return
     */
    @Bean
    public HttpServerExceptionHandler httpServerExceptionHandler(){
        return new HttpServerExceptionHandler();
    }


    /**
     * 响应处理
     * @return
     */
    @Bean
    public RespDataAdvice respDataAdvice(){
        return new RespDataAdvice();
    }

}
