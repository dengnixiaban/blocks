package cn.blocks.commonnetty.config;

import cn.blocks.commonnetty.constant.NettyConstant;
import cn.blocks.commonnetty.handler.MsgDispatcherHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Log4J2LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @description
 *          netty初始化protobuf序列化
 *
 *
 * @author Somnus丶y
 * @date 2019/9/28 15:14
 */
public class StringProtocolInitalizer extends ChannelInitializer<NioSocketChannel> {


    @Autowired
    private LoggingHandler loggingHandler;

    @Autowired
    private NettyProperties nettyProperties;


    @Override
    protected void initChannel(NioSocketChannel socketChannel) throws Exception {
        ChannelPipeline cp = socketChannel.pipeline();
        //服务端心跳检测
        cp.addLast(new IdleStateHandler(
                NettyConstant.SERVER_READ_IDEL_TIME_OUT,
                NettyConstant.SERVER_WRITE_IDEL_TIME_OUT,
                NettyConstant.SERVER_ALL_IDEL_TIME_OUT,
                TimeUnit.SECONDS));
        //cp.addLast("decoder", new FixedLengthFrameDecoder(10)).addLast(stringDecoder);
        //cp.addLast("decoder", stringDecoder);
        //cp.addLast("encoder", stringEncoder);
        //传输的协议 Protobuf
        cp.addLast(new ProtobufVarint32FrameDecoder());
//        cp.addLast(new ProtobufDecoder(Msg.Client.getDefaultInstance()));
        cp.addLast(new ProtobufVarint32LengthFieldPrepender());
        cp.addLast(new ProtobufEncoder());
        cp.addLast("handler", new MsgDispatcherHandler());
        if(nettyProperties.isLogging()){
            InternalLoggerFactory.setDefaultFactory(Log4J2LoggerFactory.getDefaultFactory());
            cp.addLast(loggingHandler);
        }
    }

}
