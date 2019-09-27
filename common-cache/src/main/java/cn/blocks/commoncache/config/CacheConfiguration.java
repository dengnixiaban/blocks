package cn.blocks.commoncache.config;

import cn.blocks.commoncache.core.CacheAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description
 *          缓存配置
 *
 * @author Somnus丶y
 * @date 2019/9/26 9:38
 */
@Configuration
public class CacheConfiguration {


    @Bean
    public CacheAspect cacheAspect(){
        return new CacheAspect();
    }


}
