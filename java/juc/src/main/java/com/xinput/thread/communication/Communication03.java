package com.xinput.thread.communication;


import com.xinput.util.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 使用JUC工具类 CountDownLatch
 * <p>
 * 问题
 * 有两个线程，A 线程向一个集合里面依次添加元素“abc”字符串，一共添加十次，当添加到第五次的时候，
 * 希望 B 线程能够收到 A 线程的通知，然后 B 线程执行相关的业务操作。
 * 线程间通信的模型有两种：共享内存和消息传递，以下方式都是基本这两种模型来实现的。
 * <p>
 */
public class Communication03 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        List<String> list = new ArrayList();

        // 线程A
        Thread threadA = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                list.add("abc");
                System.out.println("线程A添加元素，此时list的size为：" + list.size());
                Factory.sleepMillis(500);
                if (list.size() == 5) {
                    latch.countDown();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true) {
                if (list.size() != 5) {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程B收到通知，开始执行自己的业务...");
                print(list);
                break;
            }
        });

        //需要先启动线程B
        threadB.start();
        Factory.sleepMillis(1000);
        //再启动线程A
        threadA.start();
    }

    private static void print(List<String> list) {
        System.out.println("集合List: " + list);
    }
}
