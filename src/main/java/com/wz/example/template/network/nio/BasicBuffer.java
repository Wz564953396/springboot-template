package com.wz.example.template.network.nio;

import java.nio.IntBuffer;

public class BasicBuffer {

    public static void main(String[] args) {

//        创建一个容量为5的intBuffer
        IntBuffer buffer = IntBuffer.allocate(5);

//        buffer中放入元素
        for (int i = 0; i < 5; i++) {
            buffer.put(i * 2);
        }

//      buffer读写操作之间，需要 <翻转> ，否则读不出来
        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println("buffer输出：" + buffer.get());
        }

    }
}
