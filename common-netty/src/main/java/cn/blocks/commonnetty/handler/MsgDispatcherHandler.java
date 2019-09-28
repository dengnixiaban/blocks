package cn.blocks.commonnetty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/9/28 15:21
 */
public class MsgDispatcherHandler extends ChannelInboundHandlerAdapter {


    /**
     * @description
     *          处理过程中ChannelPipeline中发生错误时被调用
     *
     * @param ctx, cause
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/28
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("处理过程中ChannelPipeline中发生错误时被调用");
    }

    /**
     * 功能描述：消息读取
     * @auther: appleDemo
     * @param ctx
     * @param msg
     * @date: 2019-04-12
     * @return: void
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead接收到消息");
    }



    /**
     * @description
     *          当ChannelHandler从一个ChannelPipeline中移除时被调用
     *
     * @param ctx
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/28
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved移除通道");
        super.handlerRemoved(ctx);

    }

    /**
     * @description
     *          功能描述：指定时间没有写操作，则执行如下方法
     *
     * @param ctx, evt
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/28
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("userEventTriggered指定时间没有写操作");
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.ALL_IDLE)){

            }

        }
    }


}
