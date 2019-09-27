package cn.blocks.commoncache.annotation;

import cn.blocks.commoncache.constant.CacheType;
import cn.blocks.commoncache.loader.DataLoader;

import java.lang.annotation.*;

/**
 * @description
 *      guava cache
 *
 * @author Somnus丶y
 * @date 2019/9/27 9:41
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface GuavaCache {

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
     * 缓存类型
     * @return
     */
    CacheType type() default CacheType.GUAVA_REDIS;


    /**
     * loader
     * @return
     */
//    Class<DataLoader> dataLoader();

}
