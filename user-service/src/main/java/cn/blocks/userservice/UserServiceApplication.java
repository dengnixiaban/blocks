package cn.blocks.userservice;

import cn.blocks.commonmysql.annotation.EnableBlocksMysql;
import cn.blocks.commonmysql.config.DruidConf;
import cn.blocks.commonmysql.config.MybatisPlusConf;
import cn.blocks.commonmysql.config.SingletonConfiguration;
import cn.blocks.httpclient.annotation.EnableHttpClient;
import cn.blocks.httpserver.annotation.EnableHttpServer;
import cn.blocks.httpserver.config.AdviceConfig;
import cn.blocks.httpserver.config.SwaggerConfiguration;
import cn.blocks.httpserver.config.WebConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @description
 *      用户服务启动入口
 *
 * @author Somnus丶y
 * @date 2019/9/20
 */
@ServletComponentScan(basePackages="cn.blocks")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HttpMessageConvertersAutoConfiguration.class})
@EnableEurekaClient
@EnableHttpClient
@EnableHttpServer(defaultConfiguration = { WebConfig.class, AdviceConfig.class, SwaggerConfiguration.class })
@EnableBlocksMysql(defaultConfiguration = { SingletonConfiguration.class, DruidConf.class, MybatisPlusConf.class })
@MapperScan("cn.blocks.userservice.repository.dao")
public class UserServiceApplication implements ApplicationContextAware, CommandLineRunner {

    private ApplicationContext applicationContext;

    @Autowired
    private MongoTemplate mongoTemplate;


    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setName("111");
//        mongoTemplate.insert(userEntity);
    }

}
