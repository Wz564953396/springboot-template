package com.wz.example.template.network.netty.groupChat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class GroupChatClient {

    private final String host;

    private final int port;

    public GroupChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            ChannelFuture channelFuture = bootstrap.group(eventLoopGroup)
                    .handler(new ChannelInitializer<NioServerSocketChannel>() {
                        @Override
                        protected void initChannel(NioServerSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new ChatClientHandler());
                        }
                    }).connect(host, port).sync();
            System.out.println(String.format("--------------------%s-------------------", channelFuture.channel().localAddress()));
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
