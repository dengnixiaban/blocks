package cn.blocks.httpclient.interceptor;

import cn.blocks.commonutils.utils.LogUtils;
import cn.blocks.httpclient.config.OrderedAnnotatedElement;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @description
 *          pojo方式  get 请求feign
 *
 * @author Somnus丶y
 * @date 2019/8/30 19:10
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();

    @Override
    public void apply(RequestTemplate template) {
        Map<String, FeignClientInterceptor> interceptorMap = applicationContext.
                getBeansOfType(FeignClientInterceptor.class);
        if(MapUtils.isNotEmpty(interceptorMap)){
            Collection<FeignClientInterceptor> interceptors = interceptorMap.values();
            List<OrderedAnnotatedElement> elements = new ArrayList<>();
            interceptors.forEach(interceptor->{
                try {
                    String name = interceptor.getClass().getName();
                    elements.add(
                            new OrderedAnnotatedElement(this.metadataReaderFactory, name,interceptor));
                }
                catch (IOException e) {
                    //ignore
                    LogUtils.warn("FeignRequestInterceptor初始化异常:%s",e.getMessage());
                }
            });
            AnnotationAwareOrderComparator.sort(elements);
            elements.stream().
                    map(OrderedAnnotatedElement::getInterceptor).
                    filter(i->i.match(template))
                    .forEach(i->i.apply(template));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
