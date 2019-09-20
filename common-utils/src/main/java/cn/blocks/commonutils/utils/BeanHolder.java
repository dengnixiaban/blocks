package cn.blocks.commonutils.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.Objects;

/**
 * @description
 * @author Somnus丶y
 * @date 2019/9/20 17:03
 */
public class BeanHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanHolder.applicationContext = applicationContext;
    }


    /**
     * @description
     *          通过类型获取bean
     *
     * @param cls
     * @return T
     * @throws
     * @author Somnus丶y
     * @date 2019/9/20
     */
    public static <T> T getBeanByClass(Class<T> cls){
        return applicationContext.getBean(cls);
    }


    /**
     * @description
     *          通过类型获取bean  map
     *
     * @param cls
     * @return java.util.Map<java.lang.String,T>
     * @throws
     * @author Somnus丶y
     * @date 2019/9/20
     */
    public static <T> Map<String, T> getBeansByClass(Class<T> cls){
        return applicationContext.getBeansOfType(cls);
    }


    /**
     * @description
     *          通过 beanname获取bean
     *
     * @param name
     * @return T
     * @throws
     * @author Somnus丶y
     * @date 2019/9/20
     */
    public static <T> T getBeanByName(String name){
        Object bean = applicationContext.getBean(name);
        if(Objects.nonNull(bean)){
            return (T)bean;
        }else{
            return null;
        }
    }

}
