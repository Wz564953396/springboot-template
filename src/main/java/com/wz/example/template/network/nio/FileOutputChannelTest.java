package com.wz.example.template.network.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileOutputChannelTest {

    public static void main(String[] args) {
        String str = "hello, what's up ! ";

        FileOutputStream fileOutputStream = null;
        try {
//            创建文件输出流
            fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test1.txt");

//            获取对应的channel
            FileChannel channel = fileOutputStream.getChannel();

//            创建字节流buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);

//            将字节流放入buffer中
            buffer.put(str.getBytes());

//            转换buffer读写方向
            buffer.flip();

//            将buffer写入通道中
            channel.write(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
