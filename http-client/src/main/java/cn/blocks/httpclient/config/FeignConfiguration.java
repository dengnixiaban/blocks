package cn.blocks.httpclient.config;

import cn.blocks.httpclient.interceptor.FeignClientInterceptor;
import cn.blocks.httpclient.interceptor.FeignGetBeanInterceptor;
import cn.blocks.httpclient.interceptor.FeignLogInterceptor;
import cn.blocks.httpclient.interceptor.FeignRequestInterceptor;
import feign.Feign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @description
 *          feign配置
 *
 * @author Somnus丶y
 * @date 2019/8/30 19:03
 */
@Configuration
@ConditionalOnClass({ Feign.class})
@Order(value = 2)
public class FeignConfiguration implements HttpClientBootstrapConfiguration{

    /**
     * 禁止feign mapping到mvc
     */
    @Bean
    public WebMvcRegistrations feignWebRegistrations() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new FeignRequestMappingHandlerMapping();
            }
        };
    }

    private static class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        @Override
        protected boolean isHandler(Class<?> beanType) {
            return super.isHandler(beanType) &&
                   !AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class);
        }
    }


    /**
     * @description
     *          feign 拦截器
     *
     * @return cn.blocks.httpclient.interceptor.FeignRequestInterceptor
     * @throws
     * @author Somnus丶y
     * @date 2019/9/9
     */
    @Bean
    public FeignRequestInterceptor feignRequestInterceptor(){
        return new FeignRequestInterceptor();
    }


    @Bean
    @ConditionalOnBean(FeignRequestInterceptor.class)
    public FeignClientInterceptor feignGetBeanInterceptor(){
        return new FeignGetBeanInterceptor();
    }


    @Bean
    @ConditionalOnBean(FeignRequestInterceptor.class)
    public FeignClientInterceptor feignLogInterceptor(){
        return new FeignLogInterceptor();
    }



}