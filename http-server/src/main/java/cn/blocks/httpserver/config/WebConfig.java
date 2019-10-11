package cn.blocks.httpserver.config;

import cn.blocks.commonutils.utils.LogUtils;
import cn.blocks.httpserver.aspect.LogAspect;
import cn.blocks.httpserver.filter.DataTransmitFilter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description
 *          web配置
 *
 * @author Somnus丶y
 * @date 2019/8/30 19:22
 */
@Configuration
@Slf4j
@EnableWebMvc
public class WebConfig/* implements WebMvcConfigurer */{

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    /**
     * 增加字符串转日期的功能
     */
    @PostConstruct
    public void initEditableValidation() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter
                .getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer
                    .getConversionService();
            genericConversionService.addConverter(String.class, Date.class, new String2DateConverter());
        }
    }

    private class String2DateConverter implements Converter<String,Date> {

        @Override
        public Date convert(String source) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(source);
            } catch (ParseException e) {
                LogUtils.error(log,e,"时间转换失败");
                return null;
            }
        }
    }


    /**
     * @description
     *          消息转换 使用fastjson
     *
     * @param converters
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/11
     */
//    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        /*FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //设置默认编码
        fastJsonHttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //格式化时间
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat);
        //序列化方案
        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty
                );
        //3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //5.将convert添加到converters当中.
        //tips-httpmessageconvert  springboot 2.0会按照converters的顺序来，所以要让自定义的生效需要把他放到前面去
        converters.add(fastJsonHttpMessageConverter);*/
    }


    /**
     * @description
     *          切换成该方式
     *       {@see tips-httpmessageconvert}
     *
     * @return org.springframework.boot.autoconfigure.http.HttpMessageConverters
     * @throws
     * @author Somnus丶y
     * @date 2019/9/11
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //设置默认编码
        fastJsonHttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //格式化时间
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat);
        //序列化方案
        fastJsonConfig.setSerializerFeatures(
                //                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty
        );
        //3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastJsonHttpMessageConverter);
    }



    /**
     * @description
     *          静态资源问题
     *
     * @param registry
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/11
     */
//    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }



    /**
     * @description
     *          日志
     *
     * @return cn.blocks.httpserver.aspect.LogAspect
     * @throws
     * @author Somnus丶y
     * @date 2019/9/11
     */
    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }



    @Bean
    public DataTransmitFilter dataTransmitFilter(){
        return new DataTransmitFilter();
    }

}