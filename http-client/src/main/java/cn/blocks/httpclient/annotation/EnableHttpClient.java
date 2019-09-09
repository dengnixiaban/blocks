package cn.blocks.httpclient.annotation;

import cn.blocks.httpclient.registrar.HttpClientImportSelector;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @description
 *          装配httpclient
 *
 * @author Somnus丶y
 * @date 2019/9/6 9:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(HttpClientImportSelector.class)
@EnableFeignClients
public @interface EnableHttpClient {

}
