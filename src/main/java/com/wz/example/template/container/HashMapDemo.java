package com.wz.example.template.container;

import java.util.HashMap;

public class HashMapDemo {

    public static void main(String[] args) {
        int i = 0;
        HashMap<String, Object> map = new HashMap<>();

        while (true) {
            map.put(String.valueOf(i), i);
        }
    }

}
