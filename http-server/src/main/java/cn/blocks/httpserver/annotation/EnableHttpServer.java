package cn.blocks.httpserver.annotation;

import cn.blocks.httpserver.registrar.HttpServerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @description
 *          httpclient装配入口
 *
 * @author Somnus丶y
 * @date 2019/8/31 15:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(HttpServerRegistrar.class)
public @interface EnableHttpServer {

    /**
     * @description
     *          select import cofiguration
     *
     * @author Somnus丶y
     * @date 2019/8/31
     */
    Class<?>[] defaultConfiguration() default {};

}
