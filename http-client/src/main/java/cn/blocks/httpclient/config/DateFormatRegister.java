package cn.blocks.httpclient.config;

import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description
 *          feign调用时间转字符串传输  指定格式
 *
 *     解决传输时间少8小时问题
 *
 * @author Somnus丶y
 * @date 2019/8/30 19:19
 */
public class DateFormatRegister implements FeignFormatterRegistrar {

    public DateFormatRegister(){
    }

    @Override
    public void registerFormatters(FormatterRegistry registry) {
        registry.addConverter(Date.class, String.class, new Date2StringConverter());
    }

    private class Date2StringConverter implements Converter<Date,String> {

        @Override
        public String convert(Date source) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(source);
        }

    }
}
