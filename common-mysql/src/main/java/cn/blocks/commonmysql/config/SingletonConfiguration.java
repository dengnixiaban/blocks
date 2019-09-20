package cn.blocks.commonmysql.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description
 *          单例mysql配置
 *
 * @author Somnus丶y
 * @date 2019/9/10 12:05
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix="blocks.mysql.singleton",name = "enable", havingValue = "true")
@EnableConfigurationProperties(value = {SingletonConfigurationProperties.class})
public class SingletonConfiguration {




}
