package com.wz.example.template.network.netty.groupChat.base;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerMain {
    static ServerSocket serversocket = null;
    static List<Socket> sockets = new ArrayList<>();
    static int number = 0;
    static {
        try {
            serversocket = new ServerSocket(5000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("服务端开始工作");

        while (true) {
            Socket socket = null;
            try {
                socket = serversocket.accept();
                number++;
                sockets.add(socket);
                System.out.println(String.format("用户[%d][%s]进入房间", number, socket.getPort()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Print client = new Print(socket);
            ServerThread server = new ServerThread(socket);
            Thread read = new Thread(server);
            Thread print = new Thread(client);

            read.start();

            print.start();
        }
    }
}
