package cn.blocks.commonmongodb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description
 *          单机mongo properties
 *
 * @author Somnus丶y
 * @date 2019/9/24 11:45
 */
@Data
@ConfigurationProperties(prefix = "blocks.mongo.singleton")
public class SingleMongoProperties {

    private String address;
    private String database;
    private String username;
    private String password;
    private Integer minConnectionsPerHost = 20;
    private Integer maxConnectionsPerHost = 100;
    private Integer threadsAllowedToBlockForConnectionMultiplier = 5;
    private Integer serverSelectionTimeout = 30000;
    private Integer maxWaitTime = 120000;
    private Integer maxConnectionIdleTime = 0;
    private Integer maxConnectionLifeTime = 0;
    private Integer connectTimeout = 10000;
    private Integer socketTimeout = 0;
    private Boolean socketKeepAlive = false;
    private Boolean sslEnabled = false;
    private Boolean sslInvalidHostNameAllowed = false;
    private Boolean alwaysUseMBeans = false;
    private Integer heartbeatFrequency = 20000;
    private Integer minHeartbeatFrequency = 500;
    private Integer heartbeatConnectTimeout = 20000;
    private Integer heartbeatSocketTimeout = 10000;

}
