package cn.blocks.commoncache.core;

import cn.blocks.commoncache.annotation.CaffeineCache;
import cn.blocks.commoncache.annotation.GuavaCache;
import cn.blocks.commoncache.annotation.RedisCache;
import cn.blocks.commoncache.constant.CacheEnv;
import cn.blocks.commoncache.constant.CacheType;
import cn.blocks.commoncache.loader.CacheKeyGenerator;
import cn.blocks.commoncache.loader.DataLoader;
import cn.blocks.commonutils.exception.BlocksFrameWorkException;
import cn.blocks.commonutils.exception.ExceptionEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @description
 *      抽象数据加载器
 *
 * @author Somnus丶y
 * @date 2019/9/27 10:49
 */
@SuppressWarnings("Duplicates")
public abstract class AbstractDataLoader implements ApplicationContextAware, DataLoader {


    /**
     * @description
     *          放在这里或者postconstract都行，有空再调整位置
     *
     *
     * @param applicationContext
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/27
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        initCacheFramework();
    }



    /**
     * @description
     *      缓存框架初始化
     *
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/27
     */
    private void initCacheFramework(){
        Method[] methods = this.getClass().getMethods();
        Method method = null;
        for (Method m :methods) {
            if(m.getName().equals("getData")){
                method = m;
                break;
            }
        }
        //判断是哪种缓存  guava
        GuavaCache annotation = method.getAnnotation(GuavaCache.class);
        if(Objects.nonNull(annotation) && annotation.enable()){
            BlocksCache blocksCache = new BlocksCache(CacheType.GUAVA);
            blocksCache.setCacheName(annotation.name());
            blocksCache.setDataLoader(this);
            Class keyGenerator = annotation.keyGenerator();
            CacheKeyGenerator cacheKeyGenerator = null;
            if(!CacheKeyGenerator.class.isAssignableFrom(keyGenerator)){
                throw new BlocksFrameWorkException(ExceptionEnum.CACHEFRAMEWORKERROR,
                        keyGenerator.getName()+"必须实现CacheKeyGenerator.class");
            }else{
                try {
                    cacheKeyGenerator = (CacheKeyGenerator) keyGenerator.newInstance();
                } catch (Exception e) {
                    throw new BlocksFrameWorkException(ExceptionEnum.CACHEFRAMEWORKERROR,e);
                }
            }
            blocksCache.setCacheKeyGenerator(cacheKeyGenerator);
            //是否使用redis
            blocksCache.setUserRedis(annotation.useRedis());
            //redis失效时间
            blocksCache.setRedisExpire(annotation.redisExpire());
            CacheEnv.guavaCachePool.put(annotation.name(),blocksCache);
        }



        //redis
        RedisCache redisCache = method.getAnnotation(RedisCache.class);
        if(Objects.nonNull(redisCache) && redisCache.enable()){
            BlocksCache blocksCache = new BlocksCache(CacheType.GUAVA);
            blocksCache.setCacheName(redisCache.name());
            blocksCache.setDataLoader(this);
            Class keyGenerator = redisCache.keyGenerator();
            CacheKeyGenerator cacheKeyGenerator = null;
            if(!CacheKeyGenerator.class.isAssignableFrom(keyGenerator)){
                throw new BlocksFrameWorkException(ExceptionEnum.CACHEFRAMEWORKERROR,
                        keyGenerator.getName()+"必须实现CacheKeyGenerator.class");
            }else{
                try {
                    cacheKeyGenerator = (CacheKeyGenerator) keyGenerator.newInstance();
                } catch (Exception e) {
                    throw new BlocksFrameWorkException(ExceptionEnum.CACHEFRAMEWORKERROR,e);
                }
            }
            blocksCache.setCacheKeyGenerator(cacheKeyGenerator);
            //redis失效时间
            blocksCache.setRedisExpire(redisCache.redisExpire());
            CacheEnv.redisCachePool.put(redisCache.name(),blocksCache);
        }


        //caffeine
        CaffeineCache caffeineCache = method.getAnnotation(CaffeineCache.class);
        if(Objects.nonNull(caffeineCache) && caffeineCache.enable()){
            BlocksCache blocksCache = new BlocksCache(CacheType.CAFFEINE);
            blocksCache.setCacheName(caffeineCache.name());
            blocksCache.setDataLoader(this);
            Class keyGenerator = caffeineCache.keyGenerator();
            CacheKeyGenerator cacheKeyGenerator = null;
            if(!CacheKeyGenerator.class.isAssignableFrom(keyGenerator)){
                throw new BlocksFrameWorkException(ExceptionEnum.CACHEFRAMEWORKERROR,
                        keyGenerator.getName()+"必须实现CacheKeyGenerator.class");
            }else{
                try {
                    cacheKeyGenerator = (CacheKeyGenerator) keyGenerator.newInstance();
                } catch (Exception e) {
                    throw new BlocksFrameWorkException(ExceptionEnum.CACHEFRAMEWORKERROR,e);
                }
            }
            blocksCache.setCacheKeyGenerator(cacheKeyGenerator);
            //是否使用redis
            blocksCache.setUserRedis(caffeineCache.useRedis());
            //redis失效时间
            blocksCache.setRedisExpire(caffeineCache.redisExpire());
            CacheEnv.caffeineCachePool.put(caffeineCache.name(),blocksCache);
        }


    }

}
