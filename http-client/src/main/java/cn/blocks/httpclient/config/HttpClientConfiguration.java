package cn.blocks.httpclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @description
 *      httpclient 统一配置
 *
 * @auther Somnus丶y
 * @date 2019/9/6 15:01
 */
@Configuration
public class HttpClientConfiguration {



    @Bean
    @Primary
    public DateFormatRegister dateFormatRegister(){
        return new DateFormatRegister();
    }



}
