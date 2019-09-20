package cn.blocks.commonutils.config;

import cn.blocks.commonutils.utils.BeanHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description
 *          block所有初始化操作
 *
 * @author Somnus丶y
 * @date 2019/9/20 17:02
 */
@Configuration
public class BlocksInitConfiguration {


    @Bean
    public BeanHolder beanHolder(){
        return new BeanHolder();
    }


}
