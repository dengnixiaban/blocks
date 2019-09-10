package cn.blocks.commonmysql.annotation;

import cn.blocks.commonmysql.registrar.BlocksMysqlRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @description
 *          blocks mysql装配入口
 *
 * @author Somnus丶y
 * @date 2019/8/31 15:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(BlocksMysqlRegistrar.class)
public @interface EnableBlocksMysql {

    /**
     * @description
     *          select import cofiguration
     *
     * @author Somnus丶y
     * @date 2019/8/31
     */
    Class<?>[] defaultConfiguration() default {};

}
