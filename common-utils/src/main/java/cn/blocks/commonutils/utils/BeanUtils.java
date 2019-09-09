package cn.blocks.commonutils.utils;

import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @description
 *      spring bean utils
 *
 * @auther Somnus丶y
 * @date 2019/9/9 11:58
 */
public class BeanUtils {

    private static ApplicationContext applicationContext;


    public static <T> Map<String,T> getBeansByCls(Class<T> cls){
        return applicationContext.getBeansOfType(cls);
    }


}
