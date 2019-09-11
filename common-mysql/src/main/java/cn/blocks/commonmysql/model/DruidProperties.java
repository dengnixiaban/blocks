package cn.blocks.commonmysql.model;

import lombok.*;

/**
 * @description
 *          druid详细属性
 *
 * @auther Somnus丶y
 * @date 2019/9/10 16:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class DruidProperties {

    /**
     * 登录账户
     */
    private String loginUsername;

    /**
     * 登录密码
     */
    private String loginPassword;

    /**
     * true/false
     * 开启/禁用HTML页面上的“Reset All”功能
     */
    private String resetEnable;

    /**
     * IP白名单 (没有配置或者为空，则允许所有访问)
     */
    private String allow;

    /**
     * IP黑名单 (存在共同时，deny优先于allow)
     */
    private String deny;


    private String username;

    private String password;

    private String connectionUrl;

    private Integer initialSize;

    private Integer minIdle;

    private Integer maxActive;

    private Integer maxWait;

    private Integer timeBetweenEvictionRunsMillis;

    private Integer minEvictableIdleTimeMillis;

    private String validationQuery;

    private Boolean testWhileIdle;

    private Boolean testOnBorrow;

    private Boolean testOnReturn;

    private Boolean poolPreparedStatements;

    private Integer maxPoolPreparedStatementPerConnectionSize;

    private String filters;

}
