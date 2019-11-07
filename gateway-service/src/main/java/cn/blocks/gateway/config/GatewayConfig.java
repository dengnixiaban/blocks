package cn.blocks.gateway.config;

import cn.blocks.gateway.filter.BlocksZuulFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description
 *          gateway cofiguration
 *
 * @auther Somnus丶y
 * @date 2019/11/4 18:09
 */
@Configuration
public class GatewayConfig {


    @Bean
    public BlocksZuulFilter blocksZuulFilter(){
        return new BlocksZuulFilter();
    }

}
