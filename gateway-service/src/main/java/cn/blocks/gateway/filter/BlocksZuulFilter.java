package cn.blocks.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;

import static cn.blocks.gateway.constant.GatewayConstant.PRE;

/**
 * @description
 *          blocks 网关拦截
 *
 * @auther Somnus丶y
 * @date 2019/11/4 18:01
 */
@Slf4j
public class BlocksZuulFilter extends ZuulFilter {



    @Override
    public String filterType() {
        return PRE;
    }


    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        return null;
    }


}
