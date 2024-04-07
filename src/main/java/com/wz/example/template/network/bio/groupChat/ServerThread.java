package com.wz.example.template.network.bio.groupChat;

import java.io.*;
import java.io.BufferedReader;
import java.net.*;
import java.util.Iterator;

public class ServerThread implements Runnable {
    Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String text = in.readLine();
                System.out.println("[" + socket.getPort() + "]: " + text);
                Iterator<Socket> iterator = ServerMain.sockets.iterator();
                while (iterator.hasNext()) {
                    Socket clientSocket = iterator.next();
                    if (clientSocket != this.socket) {
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                        out.println(text);
                        out.flush();
                    }
                }
//                for (int i = 0; i < ServerMain.sockets.size(); i++) {
//                    Socket socket = ServerMain.sockets.get(i);
//                    if (socket != this.socket) {
//
//                        out.println(text);
//                    }
//                    out.flush();
//                }
            }
        } catch (IOException e) {
            System.out.println("客户离开");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

