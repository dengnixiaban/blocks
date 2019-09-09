package cn.blocks.commonutils.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @description
 *          日志工具类
 *
 * @auther Somnus丶y
 * @date 2019/9/2 10:42
 */
public class LogUtils {


    /**
     * @description
     *
     *          获取真正的caller堆栈
     *
     * @return java.lang.StackTraceElement
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    private static StackTraceElement findCaller() {
        //获取当前线程的所有堆栈信息
        StackTraceElement[] callerStackArrays = Thread.currentThread().getStackTrace();
        if(Objects.isNull(callerStackArrays)) {
            return null;
        }
        //日志类名称
        String logClassName = LogUtils.class.getName();

        //最终要返回的堆栈信息
        StackTraceElement caller = null;

        //标志获取到当前日志类标志
        boolean isLogClassName = false;

        for (StackTraceElement stackTraceElement : callerStackArrays) {
            //获取到当前类的地方
            if(logClassName.equals(stackTraceElement.getClassName())) {
                isLogClassName = true;
            }
            //下一个不是本类的就是原始的调用的类的堆栈信息
            if(!isLogClassName) {
                continue;
            }
            if(!logClassName.equals(stackTraceElement.getClassName())) {
                isLogClassName = false;
                caller = stackTraceElement;
                break;
            }
        }
        return caller;
    }

    /**
     * @description
     *
     *          获取caller栈上的logger
     *
     * @return org.slf4j.Logger
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    private static Logger getLogger() {
        StackTraceElement caller = findCaller();
        if(Objects.isNull(caller)) {
            return LoggerFactory.getLogger(LogUtils.class);
        }
        return LoggerFactory.getLogger(caller.getClassName()+"."+
                                       caller.getMethodName()+"() [line:" + caller.getLineNumber()+" ]");
    }


    /**
     * @description
     *
     *          warn
     *
     * @param message
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void warn(String message) {
        getLogger().warn(message);
    }


    /**
     * @description
     *          warn 含参
     *
     * @param message, obj
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void warn(String message, Object... obj) {
        getLogger().warn(String.format(message, obj));
    }


    /**
     * @description
     *
     *         warn   接收外部logger
     *
     * @param logger
     *          外部logger
     * @param message
     *
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void warn(Logger logger,String message) {
        logger.warn(message);
    }


    /**
     * @description
     *          format warn
     *
     * @param logger, reg, obj
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void warn(Logger logger,String reg,Object... obj) {
        logger.warn(String.format(reg,obj));
    }


    /**
     * @description
     *
     *          info
     *
     * @param message
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void info(String message) {
        getLogger().info(message);
    }


    /**
     * @description
     *          含参info
     *
     * @param message, obj
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void info(String message, Object... obj) {
        getLogger().info(String.format(message, obj));
    }


    /**
     * @description
     *
     *         info   接收外部logger
     *
     * @param logger
     *          外部logger
     * @param message
     *
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void info(Logger logger,String message) {
        logger.warn(message);
    }


    /**
     * @description
     *          format info
     *
     * @param logger, reg, obj
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void info(Logger logger,String reg,Object... obj) {
        logger.warn(String.format(reg,obj));
    }


    /**
     * @description
     *
     *          debug
     *
     * @param message
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void debug(String message) {
        getLogger().debug(message);
    }



    /**
     * @description
     *          警告日志,含参
     *
     * @param message, obj
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void debug(String message, Object... obj) {
        getLogger().debug(String.format(message, obj));
    }


    /**
     * @description
     *
     *         debug   接收外部logger
     *
     * @param logger
     *          外部logger
     * @param message
     *
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void debug(Logger logger,String message) {
        logger.warn(message);
    }


    /**
     * @description
     *          format debug
     *
     * @param logger, reg, obj
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void debug(Logger logger,String reg,Object... obj) {
        logger.warn(String.format(reg,obj));
    }



    /**
     * @description
     *
     *          错误日志，不含异常
     *
     * @param message
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(String message) {
        getLogger().error(message);
    }


    /**
     * @description
     *
     *          警告日志,含参
     *
     * @param message, obj
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(String message, Object... obj) {
        getLogger().error(String.format(message, obj));
    }


    /**
     * @description
     *
     *          错误日志，含异常
     *
     * @param message, e
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(String message,Exception e) {
        getLogger().error(message, e);
    }


    /**
     * @description
     *          警告日志,含参
     *
     * @param message, e, obj
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(Throwable e,String message,Object... obj) {
        getLogger().warn(String.format(message, obj), e);
    }




    /**
     * @description
     *
     *          错误日志，不含异常,外部logger
     *
     * @param message
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(Logger logger,String message) {
        logger.error(message);
    }


    /**
     * @description
     *
     *          警告日志,含参,外部logger
     *
     * @param message, obj
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(Logger logger,String message, Object... obj) {
        logger.error(String.format(message,obj));
    }


    /**
     * @description
     *
     *          错误日志，含异常,外部logger
     *
     * @param message, e
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(Logger logger,Throwable e,String message) {
        logger.error(message, e);
    }


    /**
     * @description
     *          警告日志,含参,外部logger
     *
     * @param message, e, obj
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(Logger logger,Throwable e,String message, Object... obj) {
        logger.warn(String.format(message, obj), e);
    }
}

