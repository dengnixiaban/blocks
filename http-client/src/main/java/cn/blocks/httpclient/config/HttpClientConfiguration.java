package cn.blocks.httpclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

/**
 * @description
 *      httpclient 统一配置
 *
 * @author Somnus丶y
 * @date 2019/9/6 15:01
 */
@Configuration
@Order(value = 1)
public class HttpClientConfiguration implements HttpClientBootstrapConfiguration{



    @Bean
    @Primary
    public DateFormatRegister dateFormatRegister(){
        return new DateFormatRegister();
    }



}
