package com.wz.example.template.network.netty.groupChat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 每一个客户端都会有自己独立的handler
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static Logger logger = LoggerFactory.getLogger(ChatServerHandler.class);

//    channel组，管理所有的客户端
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 当 ChannelHandler 被添加到 ChannelPipeline 中时被调用，不涉及channel的状态变化
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        logger.info(ChatFormatUtil.write(ctx.channel().remoteAddress().toString(), "ChatServerHandler.handlerAdded()"));
        channelGroup.add(ctx.channel());
//        向channelGroup中所有的channel写入数据，不需要自己遍历
        channelGroup.writeAndFlush(String.format("客户端[%s]加入聊天", ctx.channel().remoteAddress()));
    }

    /**
     * 当 channel 断开连接时被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.info(ChatFormatUtil.write(ctx.channel().remoteAddress().toString(), "ChatServerHandler.handlerRemoved()"));
//        不需要手动remove，触发该方法之前已经执行过remove了
//        channelGroup.remove(ctx.channel());
        channelGroup.writeAndFlush(String.format("客户端离开聊天", ctx.channel().remoteAddress()));
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        logger.info(ChatFormatUtil.write(ctx.channel().remoteAddress().toString(), "ChatServerHandler.channelRegistered()"));
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        logger.info(ChatFormatUtil.write(ctx.channel().remoteAddress().toString(), "ChatServerHandler.channelUnregistered()"));
    }

    /**
     * channel切换到 Active 状态时被调用，当 Channel 连接到远程节点并准备好传输数据时
     * 服务端打印日志
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info(String.format("用户【%s】上线", ctx.channel().remoteAddress()));
    }

    /**
     * channel切换到 Inactive 状态时被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        logger.info(String.format("用户【%s】下线", ctx.channel().remoteAddress()));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(item -> {
            if (channel != item) {
                item.writeAndFlush(msg);
            }
        });
    }


}
