package cn.blocks.commonredis.config;

import com.alibaba.fastjson.parser.ParserConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @description
 *          redis 单机配置
 *
 * @author Somnus丶y
 * @date 2019/9/20 16:06
 */
@Configuration
@EnableConfigurationProperties(value = RedisSingleProperties.class)
@ConditionalOnProperty(value = "blocks.redis.singleton.enable",havingValue = "true")
public class SingleRedisConfiguration {


    @Autowired
    RedisSingleProperties redisSingleProperties;


    /**
     * @description
     *          获取连接工厂类
     *
     * @return org.springframework.data.redis.connection.RedisConnectionFactory
     * @throws
     * @author Somnus丶y
     * @date 2019/9/20
     */
    @Bean("redisConnectionFactory")
    public RedisConnectionFactory getConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisSingleProperties.getHost());
        configuration.setPort(redisSingleProperties.getPort());
        configuration.setDatabase(redisSingleProperties.getDatabase());
        configuration.setPassword(RedisPassword.of(redisSingleProperties.getPassword()));
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration, getPoolConfig());
        //factory.setShareNativeConnection(false);//是否允许多个线程操作共用同一个缓存连接，默认true，false时每个操作都将开辟新的连接
        return factory;
    }


    /**
     * @description
     *          获取缓存连接池
     *
     * @return org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration
     * @throws
     * @author Somnus丶y
     * @date 2019/9/20
     */
    @Bean
    public LettucePoolingClientConfiguration getPoolConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(redisSingleProperties.getLettuce().getPool().getMaxActive());
        config.setMaxWaitMillis(redisSingleProperties.getLettuce().
                getPool().getMaxWait().getSeconds()*1000);
        config.setMaxIdle(redisSingleProperties.getLettuce().getPool().getMaxIdle());
        config.setMinIdle(redisSingleProperties.getLettuce().getPool().getMinIdle());
        LettucePoolingClientConfiguration pool = LettucePoolingClientConfiguration.builder()
                .poolConfig(config)
                //连接超时
                .commandTimeout(redisSingleProperties.getTimeout())
                //关闭超时
                .shutdownTimeout(redisSingleProperties.getLettuce().getShutdownTimeout())
                .build();
        return pool;
    }


    /**
     * @description
     *          获取redis的连接
     *
     * @param redisConnectionFactory
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>
     * @throws
     * @author Somnus丶y
     * @date 2019/9/20
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            @Qualifier("redisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //使用BlocksFastJsonRedisSerializer来序列化和反序列化redis的value值
//        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);
        BlocksFastJsonRedisSerializer serializer = new BlocksFastJsonRedisSerializer(Object.class);
        ParserConfig.getGlobalInstance().addAccept("cn.blocks.");

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }

}
