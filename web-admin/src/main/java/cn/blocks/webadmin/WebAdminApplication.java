package cn.blocks.webadmin;

import cn.blocks.httpclient.annotation.EnableHttpClient;
import cn.blocks.httpserver.annotation.EnableHttpServer;
import cn.blocks.httpserver.config.AdviceConfig;
import cn.blocks.httpserver.config.ValidateConfig;
import cn.blocks.httpserver.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@ServletComponentScan(basePackages="cn.blocks")
@SpringBootApplication(exclude = {HttpMessageConvertersAutoConfiguration.class})
@EnableEurekaClient
@EnableHttpClient
@EnableHttpServer(defaultConfiguration = { WebConfig.class, AdviceConfig.class, ValidateConfig.class})
//todo 配置与feignclient冲突，待解决
public class WebAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAdminApplication.class, args);
    }

}
