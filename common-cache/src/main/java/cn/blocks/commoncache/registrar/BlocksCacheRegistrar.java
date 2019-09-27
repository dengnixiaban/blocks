package cn.blocks.commoncache.registrar;

import cn.blocks.commoncache.annotation.EnableBlocksCache;
import cn.blocks.commoncache.config.CacheConfiguration;
import cn.blocks.commoncache.constant.CacheEnv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Objects;

/**
 * @description
 *          cache模块 配置注入器
 *
 * @author Somnus丶y
 * @date 2019/9/27
 */
@Slf4j
public class BlocksCacheRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(
            AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        registerDefaultConfiguration(metadata, registry);
    }

    private void registerDefaultConfiguration(AnnotationMetadata metadata, BeanDefinitionRegistry registry){
        Map<String, Object> annotationAttributes = metadata.
                getAnnotationAttributes(EnableBlocksCache.class.getName(), true);
        //判断几项缓存是否开启
        Boolean redis = (Boolean)annotationAttributes.get(CacheEnv.redisCacheName);
        if(Objects.nonNull(redis)&&redis.booleanValue()){
            //启用redis,扫描所有@RedisCache 开始构造Cache对象
            CacheEnv.enbaleCaches.put(CacheEnv.redisCacheName,true);
        }
        Boolean guava = (Boolean)annotationAttributes.get(CacheEnv.guavaCacheName);
        if(Objects.nonNull(guava)&&guava.booleanValue()){
            CacheEnv.enbaleCaches.put(CacheEnv.guavaCacheName,true);
        }
        Boolean caffeine = (Boolean)annotationAttributes.get(CacheEnv.caffeineCacheName);
        if(Objects.nonNull(caffeine)&&caffeine.booleanValue()){
            CacheEnv.enbaleCaches.put(CacheEnv.caffeineCacheName,true);
        }
        registerClientConfiguration(registry,"cache.cacheConfiguration", CacheConfiguration.class);
    }



    /**
     * @description
     *          注册
     *
     * @param registry, name, configuration
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/8/31
     */
    private void registerClientConfiguration(BeanDefinitionRegistry registry, Object name,
                                             Class configuration) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder
                .genericBeanDefinition(configuration);
        registry.registerBeanDefinition(
                name + "." + configuration.getSimpleName(),
                builder.getBeanDefinition());
    }


}
