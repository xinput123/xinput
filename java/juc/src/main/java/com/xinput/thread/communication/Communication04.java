package com.xinput.thread.communication;

import com.xinput.util.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 ReentrantLock 结合 Condition
 * <p>
 * 问题
 * 有两个线程，A 线程向一个集合里面依次添加元素“abc”字符串，一共添加十次，当添加到第五次的时候，
 * 希望 B 线程能够收到 A 线程的通知，然后 B 线程执行相关的业务操作。
 * 线程间通信的模型有两种：共享内存和消息传递，以下方式都是基本这两种模型来实现的。
 * <p>
 * 这种方式使用起来并不是很好，代码编写复杂，而且线程 B 在被 A 唤醒之后由于没有获取锁还是不能立即执行，
 * 也就是说，A 在唤醒操作之后，并不释放锁。这种方法跟 Object 的 wait()/notify() 一样。
 */
public class Communication04 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        List<String> list = new ArrayList<>();
        //线程A
        Thread threadA = new Thread(() -> {
            lock.lock();
            for (int i = 1; i <= 10; i++) {
                list.add("abc");
                System.out.println("线程A添加元素，此时list的size为：" + list.size());
                Factory.sleepMillis(500);
                if (list.size() == 5)
                    condition.signal();
            }
            lock.unlock();
        });
        //线程B
        Thread threadB = new Thread(() -> {
            lock.lock();
            if (list.size() != 5) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程B收到通知，开始执行自己的业务...");
            print(list);
            lock.unlock();
        });
        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();
    }

    private static void print(List<String> list) {
        System.out.println("集合List: " + list);
    }
}
