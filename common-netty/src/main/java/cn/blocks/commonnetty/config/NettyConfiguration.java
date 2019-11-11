package cn.blocks.commonnetty.config;

import cn.blocks.commonutils.utils.LogUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description
 *          netty配置
 *
 * @author Somnus丶y
 * @date 2019/9/28 14:55
 */
@Configuration
@EnableConfigurationProperties(NettyProperties.class)
public class NettyConfiguration {


    @Autowired
    private NettyProperties nettyProperties;

    /**
     * netty的init channel功能
     */
    @Autowired
    private StringProtocolInitalizer protocolInitalizer;


    private ChannelFuture serverChannelFuture;


    /**
     * @description
     *          获取netty启动需要的内容
     *
     * @return io.netty.bootstrap.ServerBootstrap
     * @throws
     * @author Somnus丶y
     * @date 2019/9/28
     */
    @Bean
    public ServerBootstrap bootstrap() {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup(), workerGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(protocolInitalizer);
        Map<ChannelOption<?>, Object> tcpChannelOptions = tcpChannelOptions();
        Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
        for (ChannelOption option : keySet) {
            b.option(option, tcpChannelOptions.get(option));
        }
        return b;
    }

    /**
     * @description
     *          获取boss的Nio事件组
     *
     * @return io.netty.channel.nio.NioEventLoopGroup
     * @throws
     * @author Somnus丶y
     * @date 2019/9/28
     */
    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(nettyProperties.getBossThreadCount());
    }

    /**
     * @description
     *          获取worker的事件组
     *
     * @return io.netty.channel.nio.NioEventLoopGroup
     * @throws
     * @author Somnus丶y
     * @date 2019/9/28
     */
    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(nettyProperties.getWorkerThreadCount());
    }

    /**
     * @description
     *          获取网络端口
     *
     * @return java.net.InetSocketAddress
     * @throws
     * @author Somnus丶y
     * @date 2019/9/28
     */
    @Bean
    public InetSocketAddress tcpPort() {
        return new InetSocketAddress(nettyProperties.getPort());
    }

    /**
     * @description
     *          tcp channel的操作
     *
     * @return java.util.Map<io.netty.channel.ChannelOption<?>,java.lang.Object>
     * @throws
     * @author Somnus丶y
     * @date 2019/9/28
     */
    @Bean
    public Map<ChannelOption<?>, Object> tcpChannelOptions() {
        Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();
        options.put(ChannelOption.SO_KEEPALIVE, nettyProperties.isKeepalive());
        options.put(ChannelOption.SO_BACKLOG, nettyProperties.getBacklog());
        options.put(ChannelOption.CONNECT_TIMEOUT_MILLIS, nettyProperties.getConnectTimeOut());
        options.put(ChannelOption.TCP_NODELAY, true);
        return options;
    }



    /**
     * @description
     *          日志处理
     *
     * @return io.netty.handler.logging.LoggingHandler
     * @throws
     * @author Somnus丶y
     * @date 2019/9/28
     */
    @Bean(name="loggingHandler")
    public LoggingHandler getLoggingHandler(){
        return new LoggingHandler();
    }





    /**
     * @description
     *          初始化protobuf
     *
     * @return cn.blocks.commonnetty.config.StringProtocolInitalizer
     * @throws
     * @author Somnus丶y
     * @date 2019/9/28
     */
    @Bean
    public StringProtocolInitalizer stringProtocolInitalizer(){
        return new StringProtocolInitalizer();
    }

    /**
     * @description
     *          启动netty服务
     *
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/28
     */
    @PostConstruct
    public void start() throws Exception {
        LogUtils.info("开始启动服务：" + tcpPort());
        serverChannelFuture = bootstrap().bind(tcpPort()).sync();
    }



    /**
     * @description
     *          关闭停止服务
     *
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/28
     */
    @PreDestroy
    public void stop() throws Exception {
        LogUtils.info("Tcp Server停止服务");
        //发送消息到mq，告知所有的连接全部断掉
        serverChannelFuture.channel().closeFuture().sync();
    }



}
