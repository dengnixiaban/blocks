package cn.blocks.commonmysql.config;

import cn.blocks.commonmysql.model.MpGlobalConfiguration;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.ConfigurationCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @description
 *          mybaties plus 配置
 *
 * @author Somnus丶y
 * @date 2019/9/11 12:14
 */
@Slf4j
@Configuration
public class MybatisPlusConf {




    /**
     * @description
     *          mybatisPlus全局配置
     *
     * @return cn.blocks.commonmysql.model.MpGlobalConfiguration
     * @throws
     * @author Somnus丶y
     * @date 2019/10/15
     */
    @Bean(name = "globalConfig")
    @ConfigurationProperties(prefix = "mybatis-plus.global-config")
    public MpGlobalConfiguration globalConfig() {
        return new MpGlobalConfiguration();
    }



    /**
     * @description
     *          sqlSessionFactory构造
     *
     * @param globalConfig, dataSource
     * @return org.apache.ibatis.session.SqlSessionFactory
     * @throws
     * @author Somnus丶y
     * @date 2019/10/15
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "globalConfig") MpGlobalConfiguration globalConfig,
                                               @Qualifier(value = "basisDataSource")DruidDataSource dataSource) throws Exception {
        log.info("初始化SqlSessionFactory");
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        //数据源
        sqlSessionFactory.setDataSource(dataSource);
        //全局配置
        sqlSessionFactory.setGlobalConfig(globalConfig);
        Interceptor[] interceptor = {new PaginationInterceptor()};
        //分页插件
        sqlSessionFactory.setPlugins(interceptor);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //自动扫描Mapping.xml文件
            sqlSessionFactory.setMapperLocations(resolver.getResources(globalConfig.getMapperLocations()));
            sqlSessionFactory.setConfigLocation(resolver.getResource(globalConfig.getConfigLocation()));
            sqlSessionFactory.setTypeAliasesPackage(globalConfig.getTypeAliasesPackage());
            return sqlSessionFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * MyBatis 动态扫描
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        log.info("初始化MapperScannerConfigurer");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        String basePackage = "com.ason.db.mapper";
        mapperScannerConfigurer.setBasePackage(basePackage);
        return mapperScannerConfigurer;
    }

    /**
     * 配置事务管理
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier(value = "basisDataSource") DruidDataSource dataSource) {
        log.info("初始化DataSourceTransactionManager");
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * model空值问题
     * @return
     */
    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setJdbcTypeForNull(JdbcType.NULL);
            }
        };
    }

}
