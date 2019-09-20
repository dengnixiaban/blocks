package cn.blocks.commonmysql.config;

import cn.blocks.commonutils.utils.LogUtils;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.ConfigurationCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Objects;

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


    //    mybatisPlus全局配置
    @Bean(name = "globalConfig")
    public GlobalConfiguration globalConfig(
            @Value("${mybatisPlus.globalConfig.idType}") Integer idType, //主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
            @Value("${mybatisPlus.globalConfig.fieldStrategy}") Integer fieldStrategy, //字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
            @Value("${mybatisPlus.globalConfig.dbColumnUnderline}") Boolean dbColumnUnderline, //驼峰下划线转换
            @Value("${mybatisPlus.globalConfig.isRefresh}") Boolean isRefresh, //刷新mapper 调试神器
            @Value("${mybatisPlus.globalConfig.isCapitalMode}") Boolean isCapitalMode, //数据库大写下划线转换
            @Value("${mybatisPlus.globalConfig.logicDeleteValue}") String logicDeleteValue, //逻辑删除配置
            @Value("${mybatisPlus.globalConfig.logicNotDeleteValue}") String logicNotDeleteValue //逻辑删除配置
    ) {
        LogUtils.info("初始化GlobalConfiguration");
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        if (Objects.nonNull(idType)) {
            //主键类型
            globalConfig.setIdType(idType);
        }
        if (Objects.nonNull(fieldStrategy)) {
            //字段策略
            //        globalConfig.setFieldStrategy(fieldStrategy);
        }
        if (Objects.nonNull(dbColumnUnderline)) {
            //驼峰下划线转换
            globalConfig.setDbColumnUnderline(dbColumnUnderline);
        }
        if (Objects.nonNull(isRefresh)) {
            //刷新mapper 调试神器
            //        globalConfig.setRefresh(isRefresh);
        }
        if (Objects.nonNull(isCapitalMode)) {
            //数据库大写下划线转换
            globalConfig.setCapitalMode(isCapitalMode);
        }
        if (Objects.nonNull(logicDeleteValue)) {
            //逻辑删除配置
            //        globalConfig.setLogicDeleteValue(logicDeleteValue);
        }
        if (Objects.nonNull(logicNotDeleteValue)) {
            //逻辑删除配置
            //        globalConfig.setLogicNotDeleteValue(logicNotDeleteValue);
        }
        return globalConfig;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "globalConfig")GlobalConfiguration globalConfig,
                                               @Qualifier(value = "basisDataSource")DruidDataSource dataSource) throws Exception {
        log.info("初始化SqlSessionFactory");
        String mapperLocations = "classpath*:mapper/*.xml";
        String configLocation = "classpath:mybatis-sqlconfig.xml";
        String typeAliasesPackage = "cn.blocks.userservice.repository.po.**";
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
            sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
            sqlSessionFactory.setConfigLocation(resolver.getResource(configLocation));
            sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);
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
