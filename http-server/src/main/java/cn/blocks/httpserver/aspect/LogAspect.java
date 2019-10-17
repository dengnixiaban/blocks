package cn.blocks.httpserver.aspect;

import cn.blocks.commonutils.utils.LogUtils;
import cn.blocks.httpserver.annotation.AccessLog;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description
 *          日志aop处理
 *
 * @author Somnus丶y
 * @date 2019/9/11 18:30
 */
@Slf4j
@Aspect
@EnableAspectJAutoProxy
public class LogAspect {

    @Pointcut("@annotation(cn.blocks.httpserver.annotation.AccessLog)")
    public void annotationPoinCut(){}

    @Around("annotationPoinCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        AccessLog annotation = targetMethod.getAnnotation(AccessLog.class);
        Object[] args = joinPoint.getArgs();
        List<Object> org = Stream.of(args).filter(i -> !i.getClass().getName().startsWith("org")).collect(Collectors.toList());
        LogUtils.info("%s访问日志,参数%s",annotation.desc(), JSON.toJSONString(org));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object obj = joinPoint.proceed();
        stopWatch.stop();
        LogUtils.info("%s访问日志,返回参数%s,执行时间%s",annotation.desc(),
                JSON.toJSONString(obj),stopWatch.getTotalTimeMillis());
        return obj;
    }


}
