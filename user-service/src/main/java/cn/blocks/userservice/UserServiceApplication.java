package cn.blocks.userservice;

import cn.blocks.commonmysql.annotation.EnableBlocksMysql;
import cn.blocks.commonmysql.config.SingletonConfiguration;
import cn.blocks.httpserver.annotation.EnableHttpServer;
import cn.blocks.httpserver.config.WebConfig;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@ServletComponentScan(basePackages="cn.blocks")
@SpringBootApplication
@EnableEurekaClient
@EnableHttpServer(defaultConfiguration = { WebConfig.class})
@EnableBlocksMysql(defaultConfiguration = {SingletonConfiguration.class})
public class UserServiceApplication implements ApplicationContextAware {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(1);
    }
}
