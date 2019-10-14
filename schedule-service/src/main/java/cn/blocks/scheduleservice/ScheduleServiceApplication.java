package cn.blocks.scheduleservice;

import cn.blocks.commonmysql.annotation.EnableBlocksMysql;
import cn.blocks.commonmysql.config.DruidConf;
import cn.blocks.commonmysql.config.MybatisPlusConf;
import cn.blocks.commonmysql.config.SingletonConfiguration;
import cn.blocks.httpserver.annotation.EnableHttpServer;
import cn.blocks.httpserver.config.AdviceConfig;
import cn.blocks.httpserver.config.SwaggerConfiguration;
import cn.blocks.httpserver.config.WebConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@ServletComponentScan(basePackages="cn.blocks")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class,
        MongoReactiveDataAutoConfiguration.class})
@EnableEurekaClient
@EnableHttpServer(defaultConfiguration = { WebConfig.class, AdviceConfig.class, SwaggerConfiguration.class })
@EnableBlocksMysql(defaultConfiguration = { SingletonConfiguration.class, DruidConf.class, MybatisPlusConf.class })
@MapperScan("cn.blocks.scheduleservice.mapper")
public class ScheduleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleServiceApplication.class, args);
    }

}
