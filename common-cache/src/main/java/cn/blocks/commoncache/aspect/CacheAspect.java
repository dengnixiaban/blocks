package cn.blocks.commoncache.aspect;

import cn.blocks.commoncache.annotation.GuavaCache;
import cn.blocks.commoncache.constant.CacheEnv;
import cn.blocks.commoncache.model.BlocksCache;
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
        Object resp = null;
        Boolean guava = CacheEnv.enbaleCaches.get("guava");
        if(guava&&guava.equals(true)){
            GuavaCache guavaCache = targetMethod.getAnnotation(GuavaCache.class);
            if(Objects.nonNull(guavaCache)){
                String name = guavaCache.name();
                //获取缓存
                BlocksCache blocksCache = CacheEnv.cachePool.get(name);

                //构造key
//                Object[] args = joinPoint.getArgs();
                resp = blocksCache.get("111");
            }
            return resp;
        }

        return resp;

    }


}
