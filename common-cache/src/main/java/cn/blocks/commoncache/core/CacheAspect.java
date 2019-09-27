package cn.blocks.commoncache.core;

import cn.blocks.commoncache.annotation.CaffeineCache;
import cn.blocks.commoncache.annotation.GuavaCache;
import cn.blocks.commoncache.annotation.RedisCache;
import cn.blocks.commoncache.constant.CacheEnv;
import cn.blocks.commoncache.loader.CacheKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @description
 *          cache aop
 *
 * @author Somnus丶y
 * @date 2019/9/27
 */
@Slf4j
@Aspect
@EnableAspectJAutoProxy
public class CacheAspect {

    @Pointcut("@annotation(cn.blocks.commoncache.annotation.RedisCache)")
    public void redisPoinCut(){}


    @Pointcut("@annotation(cn.blocks.commoncache.annotation.GuavaCache)")
    public void guavaPoinCut(){}


    @Pointcut("@annotation(cn.blocks.commoncache.annotation.CaffeineCache)")
    public void caffeinePoinCut(){}


    @Around("redisPoinCut()")
    public Object redisAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return doAspect(joinPoint);
    }

    @Around("guavaPoinCut()")
    public Object guavaPoinCut(ProceedingJoinPoint joinPoint) throws Throwable {
        return doAspect(joinPoint);
    }

    @Around("caffeinePoinCut()")
    public Object caffeinePoinCut(ProceedingJoinPoint joinPoint) throws Throwable {
        return doAspect(joinPoint);
    }


    private Object doAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();
        //判断是否guava缓存
        Boolean guava = CacheEnv.enbaleCaches.get(CacheEnv.guavaCacheName);
        if(Objects.nonNull(guava)&&guava.equals(true)){
            GuavaCache guavaCache = targetMethod.getAnnotation(GuavaCache.class);
            if(Objects.nonNull(guavaCache)&&guavaCache.enable()){
                String name = guavaCache.name();
                //获取缓存
                BlocksCache blocksCache = CacheEnv.guavaCachePool.get(name);
                //构造key
                CacheKeyGenerator cacheKeyGenerator = blocksCache.getCacheKeyGenerator();
                String key = cacheKeyGenerator.generate(args);
                return blocksCache.getGuava(key);
            }
        }

        //判断是否redis缓存
        Boolean redis = CacheEnv.enbaleCaches.get(CacheEnv.redisCacheName);
        if(Objects.nonNull(redis)&&redis.equals(true)){
            RedisCache redisCache = targetMethod.getAnnotation(RedisCache.class);
            if(Objects.nonNull(redisCache)&&redisCache.enable()){
                String name = redisCache.name();
                //获取缓存
                BlocksCache blocksCache = CacheEnv.redisCachePool.get(name);
                //构造key
                CacheKeyGenerator cacheKeyGenerator = blocksCache.getCacheKeyGenerator();
                String key = cacheKeyGenerator.generate(args);
                return blocksCache.getRedis(key);
            }
        }


        //判断是否caffeine缓存
        Boolean caffeine = CacheEnv.enbaleCaches.get(CacheEnv.caffeineCacheName);
        if(Objects.nonNull(caffeine)&&caffeine.equals(true)){
            CaffeineCache caffeineCache = targetMethod.getAnnotation(CaffeineCache.class);
            if(Objects.nonNull(caffeineCache)&&caffeineCache.enable()){
                String name = caffeineCache.name();
                //获取缓存
                BlocksCache blocksCache = CacheEnv.caffeineCachePool.get(name);
                //构造key
                CacheKeyGenerator cacheKeyGenerator = blocksCache.getCacheKeyGenerator();
                String key = cacheKeyGenerator.generate(args);
                return blocksCache.getCaffeine(key);
            }
        }

        //所有缓存都关闭直接执行
        return joinPoint.proceed();
    }


}
