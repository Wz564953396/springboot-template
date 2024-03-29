package com.wz.example.template.network.netty.groupChat.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Read implements Runnable {

    static Socket socket = null;

    public Read(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                System.out.println("[" + socket.getLocalPort() + "]: " + in.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
