package com.wz.example.template.service.impl;

public class StringTableTest {

    public void test1() {
        String s1 = "a" + "b" + "c";
        String s2 = "abc";

        System.out.println(s1 == s2);   //true
        System.out.println(s1.equals(s2));  //true
    }

    public void test2() {
        String s1 = "javaEE";
        String s2 = "hadoop";
        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop";    //编译器优化

//      如果拼接过程中存在变量，需要在堆空间中new一个字符串对象
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false

//        intern(): 判断字符串常量池中是否存在s6字面量的值，如果存在，则返回常量池中该字面量的地址值
        String s8 = s6.intern();
        System.out.println(s3 == s8);//true
    }

    public void test3() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        System.out.println(s3 == s4);   //false
    }

    /*
    1.字符串拼接操作不一定使用的是StringBuilder，如果拼接符号左右两边都是字符串常量或常量引用，则使用编译器优化
     */

    public void test4() {
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        System.out.println(s3 == s4);   //true
    }

    /**
     * 问题1： new String("ab")会创建几个对象？看字节码，应该是两个
     * 一个对象是：new关键字再堆空间创建的
     * 另一个对象是：字符串常量池中的对象，字节码指令：ldc
     * <p>
     * 问题2：new String("1") + new String("2");会创建几个对象？
     * 对象1：StringBuilder
     * 对象2：堆空间中new String("1")
     * 对象3：字符串常量池中”1“
     * 对象4：堆空间中new String("2")
     * 对象5：字符串常量池中”2“
     * 对象6：StringBuilder.toString()
     */
    public void test5() {
        String s = new String("1"); //执行完之后，在字符串常量池中会生成"ab"
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);    //false，s指向堆空间中的地址，s2指向字符串常量池中的地址，虽然，1.7之后也在堆中

//        String s3 = new String("ab");
        String s3 = new String("1") + new String("1");//s3变量记录的地址为:new String("11")
//        执行完上一行代码以后，字符串常量池中，是否存在”11”呢? 答案:不存在! !
        s3.intern();    //在字符串常量池中生成"11"。如何理解:  jdk6: 创建了一个新的对象"11",也就有新的地址。
        //                               jdk7: 此时常量中并没有创建"11"，而是创建一个指向堆空间中new
        String s4 = "11";   //s4变量记录的地址: 使用的是上一行代码代码执行时，在常量池中生成的”11”的地址
        System.out.println(s3 == s4);   //jdk6: false jdk7/8: true
    }

    private static final Integer MAX_COUNT = 1000 * 1000;
    private static final String[] arr = new String[MAX_COUNT];
    public void test6() {
        Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_COUNT; i++) {
            /**
             * 数组里都是新的对象，会花费大量内存
             */
//            arr[i] = new String(String.valueOf(data[i % data.length]));           //46ms

            /**
             * 虽然创建了新的对象，但是数组里放了字符串常量池里的对象，因此更节省内存
             */
            arr[i] = new String(String.valueOf(data[i % data.length])).intern();    //121ms
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为:" + (end - start));
//        try {
//            Thread.sleep(1000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.gc();
    }

    public static void main(String[] args) {
        new StringTableTest().test6();
    }
}
