package cn.blocks.httpserver.annotation;

import cn.blocks.httpserver.registrar.HttpServerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @description
 *          需要记录访问日志
 *
 * @author Somnus丶y
 * @date 2019/9/11 18:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Import(HttpServerRegistrar.class)
public @interface AccessLog {

    String desc() default "";

}
