package cn.blocks.userservice;

import cn.blocks.httpserver.annotation.EnableHttpServer;
import cn.blocks.httpserver.config.WebConfig;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
@EnableEurekaClient
@EnableHttpServer(defaultConfiguration = { WebConfig.class})
public class UserServiceApplication implements ApplicationContextAware {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(1);
    }
}
