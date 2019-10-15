package cn.blocks.commonmysql.model;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import lombok.Data;

/**
 * @description
 *          扩展GlobalConfiguration
 *
 * @auther Somnus丶y
 * @date 2019/10/15 9:16
 */
@Data
public class MpGlobalConfiguration extends GlobalConfiguration {

    private String mapperLocations = "classpath*:mapper/*.xml";

    private String configLocation = "classpath:mybatis-sqlconfig.xml";

    private String typeAliasesPackage = "cn.blocks.*";

}
