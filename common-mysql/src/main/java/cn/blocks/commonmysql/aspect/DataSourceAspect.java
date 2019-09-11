package cn.blocks.commonmysql.aspect;

import cn.blocks.commonmysql.config.DBContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @description
 *          动态获取数据源切换
 *
 * @auther Somnus丶y
 * @date 2019/9/11 18:20
 */
@Aspect
@EnableAspectJAutoProxy
public class DataSourceAspect {


    /**
     * 判断哪些需要读从数据库，其余的走主数据库
     */
    @Before("execution(*cn.blocks..*.*(..))")
    public void before(JoinPoint jp) {
        String methodName = jp.getSignature().getName();

        if (methodName.startsWith("get")||methodName.startsWith("select")||methodName.startsWith("find")||methodName.startsWith("query")) {
            DBContextHolder.slave();
        }else {
            DBContextHolder.master();
        }
    }


}
