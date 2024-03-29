package com.wz.example.template.network.netty.groupChat.base;

import java.io.*;
import java.net.*;
import java.util.*;

public class Print implements Runnable {
    Socket socket;
    Scanner in = new Scanner(System.in);

    public Print(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            while (true) {
                String msg = in.next();
//                for (int i = 0; i < number - 1; i++) {
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    out.println("服务端说：" + msg);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}