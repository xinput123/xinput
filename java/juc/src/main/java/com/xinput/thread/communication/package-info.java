/**
 * <p>
 * 问题
 * 有两个线程，A 线程向一个集合里面依次添加元素“abc”字符串，一共添加十次，当添加到第五次的时候，
 * 希望 B 线程能够收到 A 线程的通知，然后 B 线程执行相关的业务操作。
 * 线程间通信的模型有两种：共享内存和消息传递，以下方式都是基本这两种模型来实现的。
 * <p>
 * 多线程的5中通信方式:
 * 1、使用 volatile 关键字
 * 2、使用 Object 类的 wait（）/notify（）
 * 3、使用JUC工具类 CountDownLatch
 * 4、使用 ReentrantLock 结合 Condition
 * 5、基本 LockSupport 实现线程间的阻塞和唤醒
 */
package com.xinput.thread.communication;