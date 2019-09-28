package cn.blocks.commonnetty.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description
 *          netty 属性
 *
 * @author Somnus丶y
 * @date 2019/9/28 14:56
 */
@Data
@ConfigurationProperties(prefix = "blocks.netty")
public class NettyProperties {

    /**
     * 端口号
     */
    private int port;

    /**
     * 主工作线程
     */
    private int bossThreadCount;

    /**
     * 次工作线程
     */
    private int workerThreadCount;

    /**
     * 线程保存长连接
     */
    private boolean keepalive;

    /**
     *
     */
    private int backlog;

    /**
     * 心跳次数
     */
    private int freeHeartCount;

    /**
     * 连接超时时间
     */
    private int connectTimeOut;

    /**
     * 日志打印的开关
     */
    private boolean logging;


}
