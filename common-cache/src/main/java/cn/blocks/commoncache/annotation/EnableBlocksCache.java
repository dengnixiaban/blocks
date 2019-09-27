package cn.blocks.commoncache.annotation;

import cn.blocks.commoncache.registrar.BlocksCacheRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @description
 *      缓存框架启动器
 *
 * @author Somnus丶y
 * @date 2019/9/27 9:41
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value = BlocksCacheRegistrar.class)
public @interface EnableBlocksCache {

    /**
     * 启用
     * @return
     */
    boolean enable() default true;

    /**
     * 启用redis
     * @return
     */
    boolean redis() default false;

    /**
     * 启用redis
     * @return
     */
    boolean guava() default false;

    /**
     * 启用redis
     * @return
     */
    boolean caffeine() default false;



}
