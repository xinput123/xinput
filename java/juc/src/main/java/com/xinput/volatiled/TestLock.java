package com.xinput.volatiled;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步显示锁
 */
public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(ticket, "1号窗口").start();
        new Thread(ticket, "2号窗口").start();
        new Thread(ticket, "3号窗口").start();
    }
}

class Ticket implements Runnable {
    private int ticket = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        //这样买票没有问题
        //while (ticket > 0) {
        //    System.out.println(Thread.currentThread().getName() + "完成售票，余票为" + --ticket);
        //}

        //放大问题出现的记录 出现了负票号
        //while (true) {
        //    if (ticket > 0) {
        //        try {
        //            Thread.sleep(200);
        //            System.out.println(Thread.currentThread().getName() + "完成售票，余票为" + --ticket);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //    }
        //}

        //显式加锁和释放锁
        while (true) {
            lock.lock();
            try {
                if (ticket > 0) {
                    try {
                        Thread.sleep(200);
                        System.out.println(Thread.currentThread().getName() + "完成售票，余票为" + --ticket);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
