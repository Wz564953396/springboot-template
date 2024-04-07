package com.wz.example.template.network.netty.groupChat;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class ChatFormatUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String write(String name, String message) {
        return sdf.format("[" + LocalDateTime.now()) + "]" + name + ": " + message + "\n";
    }
}
