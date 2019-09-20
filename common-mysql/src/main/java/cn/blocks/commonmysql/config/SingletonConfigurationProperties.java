package cn.blocks.commonmysql.config;

import cn.blocks.commonmysql.model.DruidProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description
 *          单例mysql配置
 *
 * @author Somnus丶y
 * @date 2019/9/10 12:03
 */
@Data
@ConfigurationProperties(prefix = "blocks.mysql.singleton")
public class SingletonConfigurationProperties {

    private DruidProperties druid;

}
