package cn.blocks.scheduleservice;

import cn.blocks.httpserver.annotation.EnableHttpServer;
import cn.blocks.httpserver.config.AdviceConfig;
import cn.blocks.httpserver.config.SwaggerConfiguration;
import cn.blocks.httpserver.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;

@SpringBootApplication(exclude = {HttpMessageConvertersAutoConfiguration.class})
@EnableHttpServer(defaultConfiguration = { WebConfig.class, AdviceConfig.class, SwaggerConfiguration.class })
public class ScheduleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleServiceApplication.class, args);
    }

}
