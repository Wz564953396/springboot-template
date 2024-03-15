package com.wz.example.template.container;

import java.util.HashMap;

public class HashMapDemo {

    public static void main(String[] args) {
        int i = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        while (true) {
            System.out.println(i);
            map.put(i & 16, i);
            i++;
        }
    }

}
