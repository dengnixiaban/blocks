package cn.blocks.commonutils.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.RequestMappingInfo;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description
 *          统一权限验证工具
 *
 * @auther Somnus丶y
 * @date 2019/10/23 9:47
 */
public class AuthUtils implements ApplicationContextAware {


    private static ApplicationContext applicationContext;


    public static void checkMapping(){
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        String checkTokenPath = "com.xxxx.base.annotation.CheckToken";
        Annotation[] annotationArr;
        Set<PathPattern> path;
        List<String> list;
        for (Map.Entry<RequestMappingInfo, HandlerMethod> e : map.entrySet()){
            annotationArr = e.getValue().getMethod().getDeclaredAnnotations();
            for (Annotation a : annotationArr){
                if (checkTokenPath.equals(a.annotationType().getName())){
                    path = e.getKey().getPatternsCondition().getPatterns();
                }
            }
        }
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AuthUtils.applicationContext = applicationContext;
    }


}
