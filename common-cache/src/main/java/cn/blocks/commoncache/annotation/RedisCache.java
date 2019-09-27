package cn.blocks.commoncache.annotation;

import cn.blocks.commoncache.loader.DataLoader;

import java.lang.annotation.*;

/**
 * @description
 *      redis cache
 *
 * @author Somnus丶y
 * @date 2019/9/27 9:41
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCache {

    /**
     * 启用
     * @return
     */
    boolean enable() default true;

    /**
     * cache name
     * @return
     */
    String name();


    /**
     * loader
     * @return
     */
    Class<DataLoader> dataLoader();

}
