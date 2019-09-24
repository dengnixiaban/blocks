package cn.blocks.commonredis.config;

import cn.blocks.commonredis.service.IRedisClient;
import cn.blocks.commonredis.service.SingleRedisClient;
import com.alibaba.fastjson.parser.ParserConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

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


    /*******************************************reactive************************************************/

    @Primary
    @Bean("reactiveRedisConnectionFactory")
    public ReactiveRedisConnectionFactory lettuceConnectionFactory() {

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//                .useSsl().and()
                .commandTimeout(Duration.ofSeconds(2))
                .shutdownTimeout(Duration.ZERO)
                .build();
        RedisStandaloneConfiguration redisStandaloneConfiguration = new
                RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisSingleProperties.getHost());
        redisStandaloneConfiguration.setPort(redisStandaloneConfiguration.getPort());
        redisStandaloneConfiguration.setDatabase(redisSingleProperties.getDatabase());
        return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);
    }


    @Bean
    @ConditionalOnMissingBean(name = "reactiveRedisTemplate")
    @ConditionalOnBean(ReactiveRedisConnectionFactory.class)
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory, ResourceLoader resourceLoader) {
        /*JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(
                resourceLoader.getClassLoader());*/

        BlocksFastJsonRedisSerializer serializer = new BlocksFastJsonRedisSerializer(Object.class);
        ParserConfig.getGlobalInstance().addAccept("cn.blocks.");
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        RedisSerializationContext.RedisSerializationContextBuilder<String, Object>
                builder = RedisSerializationContext
                .newSerializationContext();
        RedisSerializationContext<String, Object> serializationContext =
                builder.key(stringRedisSerializer)
                .value(serializer)
                .hashKey(stringRedisSerializer)
                .hashValue(serializer).build();
        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, serializationContext);
    }






    /**
     * @description
     *          单例redis 服务bean
     *
     * @return cn.blocks.commonredis.service.IRedisClient
     * @throws
     * @author Somnus丶y
     * @date 2019/9/20
     */
    @Bean
    public IRedisClient SingleRedisClient(){
        return new SingleRedisClient();
    }


}
