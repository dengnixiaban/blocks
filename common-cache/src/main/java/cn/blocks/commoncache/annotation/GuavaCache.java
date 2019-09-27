package cn.blocks.commoncache.annotation;

import cn.blocks.commoncache.constant.CacheType;
import cn.blocks.commoncache.loader.CacheKeyGenerator;
import cn.blocks.commoncache.loader.impl.DefaultCacheKeyGenerator;

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
     * key生成器
     * @return
     */
    Class keyGenerator() default DefaultCacheKeyGenerator.class;

    /**
     * 是否使用redis
     * @return
     */
    boolean useRedis() default false;

    /**
     * redis失效 默认1小时
     *
     * @return
     */
    int redisExpire() default 1*60*60;


}
