package com.xinput.volatiled;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 不具备原子性
 */
public class TestAtomicDemo {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    /**
     * 有问题的代码示例
     * 主存: 存放共享数据 int i = 0;
     * 线程1: int temp=i; i = i+1; 这个时候 i = 1; 但是还未来得及写入主存，线程2来了；
     * 线程2: 从主存中读取到的还是 0；int temp=i; i = i+1; 这里 i 变成了 1
     */
    public static void test1() {
        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }

    /**
     * 原子变量：在 java.util.concurrent.atomic 包下提供了一些原子变量。
     * 1. volatile 保证内存可见性
     * 2. CAS（Compare-And-Swap） 算法保证数据变量的原子性
     * CAS 算法是硬件对于并发操作的支持
     * CAS 包含了三个操作数：
     * ①内存值  V
     * ②预估值  A
     * ③更新值  B
     * 当且仅当 V == A 时， V = B; 否则，不会执行任何操作。
     */
    public static void test2() {
        AtomicDemo2 ad = new AtomicDemo2();
        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }
}

class AtomicDemo implements Runnable {

    private int serialNumber = 0;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
    }

    public int getSerialNumber() {
        return serialNumber++;
    }
}

class AtomicDemo2 implements Runnable {

    private AtomicInteger serialNumber = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
    }

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }
}
