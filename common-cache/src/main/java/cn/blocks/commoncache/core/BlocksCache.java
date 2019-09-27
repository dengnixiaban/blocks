package cn.blocks.commoncache.core;

import cn.blocks.commoncache.config.CacheRedisUtils;
import cn.blocks.commoncache.constant.CacheType;
import cn.blocks.commoncache.loader.CacheKeyGenerator;
import cn.blocks.commoncache.loader.DataLoader;
import cn.blocks.commonutils.utils.BeanHolder;
import cn.blocks.commonutils.utils.LogUtils;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *          缓存主体
 *
 * @author Somnus丶y
 * @date 2019/9/27 11:02
 */
@SuppressWarnings("Duplicates")
@Data
@Slf4j
public class BlocksCache {

    /**
     * 缓存名称
     */
    @Setter(AccessLevel.PACKAGE)
    private String cacheName;


    /**
     * 数据加载器
     */
    @Setter(AccessLevel.PACKAGE)
    private DataLoader<String,Object> dataLoader;

    /**
     * guava
     */
    @Setter(AccessLevel.PRIVATE)
    private LoadingCache<String, Object> loadingCache;

    /**
     * key 生成器
     */
    @Setter(AccessLevel.PACKAGE)
    private CacheKeyGenerator cacheKeyGenerator;

    /**
     * 是否使用redis
     */
    @Setter(AccessLevel.PACKAGE)
    private boolean userRedis;


    /**
     * 是否使用redis
     */
    @Setter(AccessLevel.PACKAGE)
    private int redisExpire;

    /**
     * redistemplate
     */
    private RedisTemplate redisTemplate;

    /**
     * caffeine缓存对象
     */
    private com.github.benmanes.caffeine.cache.LoadingCache<String, Object> caffeineCache;


    /**
     * @description
     *          在构造函数中初始化缓存对象
     *
     * @return
     * @throws
     * @author Somnus丶y
     * @date 2019/9/27
     */
    public BlocksCache(CacheType type){
        switch (type){
            case GUAVA:
                loadingCache = CacheBuilder.newBuilder().build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String key) throws Exception {
                        if(userRedis){
                            if(Objects.isNull(redisTemplate)){
                                redisTemplate = BeanHolder.getBeanByName("redisTemplate");
                            }
                            String redisCacheKey = CacheRedisUtils.getRedisCacheKey(key);
                            Object cache = redisTemplate.opsForValue().get(redisCacheKey);
                            if(Objects.nonNull(cache)){
                                return cache;
                            }else{
                                //加载
                                Object data = dataLoader.getData(key);
                                redisTemplate.opsForValue().set(redisCacheKey,data);
                                redisTemplate.expire(redisCacheKey,redisExpire, TimeUnit.SECONDS);
                                return data;
                            }
                        }else{
                            return dataLoader.getData(key);
                        }
                    }
                });
                break;
            case REDIS:
                break;
            case CAFFEINE:
                caffeineCache =
                        Caffeine.newBuilder()
                        .build(new com.github.benmanes.caffeine.cache.CacheLoader<String, Object>() {
                            @Nullable
                            @Override
                            public Object load(@NonNull String key) throws Exception {
                                if(userRedis){
                                    if(Objects.isNull(redisTemplate)){
                                        redisTemplate = BeanHolder.getBeanByName("redisTemplate");
                                    }
                                    String redisCacheKey = CacheRedisUtils.getRedisCacheKey(key);
                                    Object cache = redisTemplate.opsForValue().get(redisCacheKey);
                                    if(Objects.nonNull(cache)){
                                        return cache;
                                    }else{
                                        //加载
                                        Object data = dataLoader.getData(key);
                                        redisTemplate.opsForValue().set(redisCacheKey,data);
                                        redisTemplate.expire(redisCacheKey,redisExpire, TimeUnit.SECONDS);
                                        return data;
                                    }
                                }else{
                                    return dataLoader.getData(key);
                                }
                            }
                        });
                break;
            default:
                break;
        }

    }



    /**
     * @description
     *          从guava获取
     *
     * @param key
     * @return java.lang.Object
     * @throws
     * @author Somnus丶y
     * @date 2019/9/27
     */
    public Object getGuava(String key){
        Object resp = null;
        try {
            resp = loadingCache.get(key);
        } catch (ExecutionException e) {
            LogUtils.error(log,e,"缓存加载失败,name:%s,key:%s",cacheName,key);
        }
        return resp;
    }


    /**
     * @description
     *          从redis获取
     *
     * @param key
     * @return java.lang.Object
     * @throws
     * @author Somnus丶y
     * @date 2019/9/27
     */
    public Object getRedis(String key){
        if(Objects.isNull(redisTemplate)){
            redisTemplate = BeanHolder.getBeanByName("redisTemplate");
        }
        String redisCacheKey = CacheRedisUtils.getRedisCacheKey(key);
        Object cache = redisTemplate.opsForValue().get(redisCacheKey);
        if(Objects.nonNull(cache)){
            return cache;
        }else{
            //加载
            Object data = dataLoader.getData(key);
            redisTemplate.opsForValue().set(redisCacheKey,data);
            redisTemplate.expire(redisCacheKey,redisExpire, TimeUnit.SECONDS);
            return data;
        }
    }


    /**
     * @description
     *          从caffeine获取
     *
     * @param key
     * @return java.lang.Object
     * @throws
     * @author Somnus丶y
     * @date 2019/9/27
     */
    public Object getCaffeine(String key){
        Object resp = null;
        try {
            resp = caffeineCache.get(key);
        } catch (Exception e) {
            LogUtils.error(log,e,"缓存加载失败,name:%s,key:%s",cacheName,key);
        }
        return resp;
    }



}
