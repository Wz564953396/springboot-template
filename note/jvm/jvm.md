# jvm学习笔记

## 一、类的加载
![1686621492281.png](1686621492281.png)
![img_9.png](img_9.png)

![img.png](img.png)

![img_1.png](img_1.png)

![img_2.png](img_2.png)

![img_3.png](img_3.png)

![img_5.png](img_5.png)

![img_4.png](img_4.png)

![img_6.png](img_6.png)

#### 双亲委派机制
![img_7.png](img_7.png)
![img_8.png](img_8.png)

## 二、运行时数据区
### 1.程序计数器（Program Counter Register）
![img_10.png](img_10.png)
    作用：用来存储只想下一条指令的地址，将要执行的指令代码，有执行引擎读取下一条指令

    · 它是一块很小的内存空间，几乎可以忽略不计，也是运行速度最快的存储区域
    · 在jvm规范中，每个线程都有它自己的程序计数器，是线程私有的，生命周期与线程保持一致
    · 任何时间一个线程都只有一个方法在执行，也就是所谓的当前方法，程序计数器会存储当前线程正在执行的java方法的jvm指令地址。如果是在执行native方法，则是未指定值(undefined)
    · 他是程序控制流的指示器，分支、循环、跳转、异常处理、线程恢复、等基础功能都需要依赖程序计数器来完成
    · 字节码解释器工作时就是通过改变这个计数器的值来选取下一个要执行的字节码指令
    · 他是唯一一个在java虚拟机规范中没有规定任何OutOfMemoryError情况的区域

#### 常见问题

>(1) 使用pc寄存器存储字节码指令地址有什么用，为什么记录当前现成的执行地址？
> 
>  答：因为CPU需要不停的切换线程，切换回来后需要知道从哪里开始继续执行，JVM字节码解释器需要通过改变PC寄存器的值来确定下一条该执行什么字节码指令

>(2) pc寄存器为什么被设定为线程私有？
>
>  答：我们都知道所谓的多线程在一个特定的时间段内只会执行其中某一个线程的方法，CPU会不停地做任务切换，这样必然导致经常中断或恢复，如何保证分毫无差呢?为了能够准确地记录各个线程正在执行的当前字节码指令地址，最好的办法自然是为每一个线程都分配一个PC寄存器，这样一来各个线程之间便可以进行独立计算，从而不会出现相互干扰的情况。
>
>由于CPU时间片轮限制，众多线程在并发执行过程中，任何一个确定的时刻，一个处理器或者多核处理器中的一个内核，只会执行某个线程中的一条指令。
>
>这样必然导致经常中断或恢复，如何保证分毫无差呢?每个线程在创建后，都会产生自己的程序计数器和栈帧，程序计数器在各个线程之间互不影响。

### 2.java虚拟机栈
![img_11.png](img_11.png)

![img_12.png](img_12.png)

![img_13.png](img_13.png)

![img_14.png](img_14.png)

![img_15.png](img_15.png)

#### ① 局部变量表（local variables）
![img_16.png](img_16.png)

![img_17.png](img_17.png)

![img_18.png](img_18.png)

![img_19.png](img_19.png)

#### ② 操作数栈（operand stack）（表达式栈）
![img_20.png](img_20.png)

![img_21.png](img_21.png)

```java
public class Test {
    public void testJvm() {
        byte i = 15;
        int j = 8;
        int k = i + j;
    }
}
```
![img_22.png](img_22.png)

##### 栈顶缓存技术
![img_23.png](img_23.png)

#### ③ 动态链接
![img_24.png](img_24.png)

![img_25.png](img_25.png)

>为什么需要常量池？
为了提供一些符号和常量，便于指令的识别

#### ④ 方法返回地址
![img_26.png](img_26.png)

### 3.本地方法栈
![img_27.png](img_27.png)

![img_28.png](img_28.png)

### 4.堆
![img_29.png](img_29.png)

![img_30.png](img_30.png)

![img_31.png](img_31.png)

```java
public class HeapSpaceInitial {
    public static void main(String[] args) {
//        返回ava虚越机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
//        返回Java虚拟机试图使用的最大雄内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        
        System.out.println("-Xms :" + initialMemory + "m");
        System.out.println("-Xmx : " + maxMemory + "m");
        System.out.println("系统内存大小为: " + initialMemory * 64 / 1024 + "G");
        System.out.printIn("系统内存大小为: " + maxMemory * 4 / 1024 + "G");
    }
}
```

查看java进程的堆内存分配使用情况
```shell
jstat -gc ${pid}
```

![img_32.png](img_32.png)

![img_33.png](img_33.png)

>默认情况下，新生代老年代的比例是1:2;
新生代中Eden区、Survivor1、Survivor2分配的内存比例是8:1:1 //实际上压根不是8:1:1，加上参数```-XX:-UseAdaptiveSizePolicy``` 不适用自适应策略，一样还是不管用

>几乎所有的java对象都是在Eden区被new出来的，除非对象大道Eden区放不下

>绝大多数的java对象的销毁都是在新生代进行的    