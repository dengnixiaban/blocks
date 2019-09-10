package cn.blocks.commonmysql.config;

import cn.blocks.commonutils.utils.LogUtils;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 *          单例mysql配置
 *
 * @auther Somnus丶y
 * @date 2019/9/10 12:05
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix="blocks.mysql.singleton",name = "enable", havingValue = "true")
@EnableConfigurationProperties(value = {SingletonConfigurationProperties.class})
public class SingletonConfiguration {


    @Bean
    @ConfigurationProperties(prefix = "blocks.mysql.singleton.datasource")
    public DataSource singletonDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean
    public ServletRegistrationBean druidServlet(SingletonConfigurationProperties properties) {
        LogUtils.info("开始初始化druid servlet configuration");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", properties.getDruid().getLoginUsername());
        initParameters.put("loginPassword", properties.getDruid().getLoginPassword());
        initParameters.put("resetEnable", "false");
        //initParameters.put("allow", allowIp); // IP白名单 (没有配置或者为空，则允许所有访问)
        //initParameters.put("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
        servletRegistrationBean.setInitParameters(initParameters);
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
