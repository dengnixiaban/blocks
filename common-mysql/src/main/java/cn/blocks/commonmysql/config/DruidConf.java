package cn.blocks.commonmysql.config;

import cn.blocks.commonmysql.model.DruidProperties;
import cn.blocks.commonutils.utils.LogUtils;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description
 *          druid配置
 *
 * @auther Somnus丶y
 * @date 2019/9/11 11:56
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Data
@Slf4j
@Configuration
@ConditionalOnBean(SingletonConfigurationProperties.class)
public class DruidConf {


    /**
     * @description
     *          配置数据源
     * @return com.alibaba.druid.pool.DruidDataSource
     * @throws
     * @author Somnus丶y
     * @date 2019/9/11
     */
    @Bean(name = "basisDataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource initDataSource(SingletonConfigurationProperties singletonConfigurationProperties) {
        LogUtils.info(log,"初始化DruidDataSource");
        DruidProperties druid = singletonConfigurationProperties.getDruid();
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName("com.mysql.jdbc.Driver");
        dds.setUrl(druid.getConnectionUrl());
        dds.setUsername(druid.getUsername());
        dds.setPassword(druid.getPassword());
        dds.setInitialSize(druid.getInitialSize());
        dds.setMinIdle(druid.getMinIdle());
        dds.setMaxActive(druid.getMaxActive());
        dds.setMaxWait(druid.getMaxWait());
        dds.setTimeBetweenEvictionRunsMillis(druid.getTimeBetweenEvictionRunsMillis());
        dds.setMinEvictableIdleTimeMillis(druid.getMinEvictableIdleTimeMillis());
        dds.setValidationQuery(druid.getValidationQuery());
        dds.setTestWhileIdle(druid.getTestWhileIdle());
        dds.setTestOnBorrow(druid.getTestOnBorrow());
        dds.setTestOnReturn(druid.getTestOnReturn());
        dds.setPoolPreparedStatements(druid.getPoolPreparedStatements());
        dds.setMaxPoolPreparedStatementPerConnectionSize(druid.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            dds.setFilters(druid.getFilters());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dds;
    }

    @Bean
    public ServletRegistrationBean druidServlet(SingletonConfigurationProperties singletonConfigurationProperties) {
        LogUtils.info("开始初始化druid servlet configuration");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        DruidProperties druid = singletonConfigurationProperties.getDruid();
        //设置登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", druid.getLoginPassword());
        servletRegistrationBean.addInitParameter("loginPassword", druid.getLoginPassword());
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        //initParameters.put("allow", allowIp); // IP白名单 (没有配置或者为空，则允许所有访问)
        //initParameters.put("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
