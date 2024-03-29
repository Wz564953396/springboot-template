package com.wz.example.template.network.netty.groupChat.base;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
    static Socket socket = null;
    Scanner input = new Scanner(System.in);
    static String name = null;

    public static void main(String args[]) {
        int x = (int) (Math.random() * 100);
        Client.name = "client" + x;
        System.out.println("欢迎进入");
        try {
            socket = new Socket("127.0.0.1", 5000);
            System.out.println(String.format("[%d]已经连上服务器", socket.getLocalPort()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Client t = new Client();
        Read read = new Read(socket);
        Thread print = new Thread(t);
        Thread reading = new Thread(read);
        print.start();
        reading.start();
    }

    public void run() {

        try {

            Thread.sleep(1000);

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (true) {

                String msg = input.next();

                out.println(msg);
                out.flush();

            }
        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}