package com.xinput.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 信号量模型：
 * 一个计数器、一个等待队列、三个方法
 * <p>
 * 三个方法：
 * init() 设置计数器的初始值
 * down() 计数器的值减1.如果此时计数器的值小于0，则当前线程将被阻塞，否则当前线程可以继续执行
 * up() 计数器的值加1如果此时计数器的值小于或者等于0，则唤醒等待队列中的一个线程。
 * <p>
 * Java SDK并发包找那个，down()和up()对应的则是acquire()和release().
 * down() 和 up() 是成对出现的
 */
public class SemaphoreDemo02 {
    static int count;

    /**
     * 初始化信号量
     */
    static final Semaphore s = new Semaphore(1);

    /**
     * 用信号量保证互斥
     * <p>
     * 假设两个线程 T1 和 T2 同时访问addOne()方法，当它们同时调用acquire()的时候，由于acquire()是一个
     * 原子操作，所以只能有一个线程(假设T1)把信号量的计数器减为0，另一个线程(T2)则是将计数器减为-1。对于
     * 线程T1，信号量里面的计数器的值是0，大于等于0，所以线程T1会继续执行；对于线程T2，信号量里面的计数器
     * 的值是 -1，小于0，按照信号量模型里对 down()的描述，线程2将被阻塞。所以此时只有线程T1会进入临界区
     * 执行 count += 1；
     * 当线程T1执行release()操作，也就是UP()操作的时候，信号量里计数器的值是-1，加1之后的值是0，小于等于0，
     * 按照信号量模型里对down()操作的描述，线程T2将被唤醒。于是T2在T1执行完临界区代码之后才获得了进去临界区
     * 执行的机会，从而保证了互斥性。
     */
    static void addOne() throws InterruptedException {
        s.acquire();
        try {
            count += 1;
        } finally {
            s.release();
        }
    }
}
