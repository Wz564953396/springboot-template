package com.wz.example.template.algorithm.search;

import java.util.ArrayList;

/**
 * 二分法搜索
 */
public class BinarySearch {

    private int[] arr = {1, 3, 5, 7, 9, 11, 13};

    private int target = 11;

    private int binarySearch(int[] array, int target) {

        return 0;
    }

    public static void main(String[] args) {
        int flag = 128;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(127);
        list.add(128);
        list.add(128);
        list.add(129);
        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).intValue() == flag) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }

}
