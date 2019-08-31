package cn.blocks.webadmin.config;

import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/8/30 19:19
 */
@Component
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
