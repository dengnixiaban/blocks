package cn.blocks.httpserver.annotation;

import java.lang.annotation.*;

/**
 * @description
 *       拒绝advice
 *
 * @author Somnus丶y
 * @date 2019/9/11 17:49
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface ExcludAdvice {

}
