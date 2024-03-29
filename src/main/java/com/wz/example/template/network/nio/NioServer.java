package com.wz.example.template.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    public static void main(String[] args) throws IOException {

//        创建一个SocketChannel
        ServerSocketChannel channel = ServerSocketChannel.open();

//        通道绑定6666端口，在服务端监听
        channel.socket().bind(new InetSocketAddress(6666));

//        将通道设置为非阻塞的
        channel.configureBlocking(false);

//        获取一个Selector
        Selector selector = Selector.open();

//        把channel注册到selector，并制定关心事件为 OP_ACCEPT
        channel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }
//            获取该selector下注册过的selectKey
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = channel.accept();
                    System.out.println("客户端连接成功，生成了一个socketChannel" + socketChannel.hashCode());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_ACCEPT, ByteBuffer.allocate(1024));

                }
                if (key.isReadable()) {
                    SocketChannel channel1 = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel1.read(buffer);
                    System.out.println("form客户端" + new String(buffer.array()));
                }
                iterator.remove();
            }
        }
    }
}
