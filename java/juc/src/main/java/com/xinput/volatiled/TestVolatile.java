package com.xinput.volatiled;

/**
 * 内存可见性
 * 是指当某个线程正在使用对象状态而另一个线程在同时修改该状态，需要确保当一个线程修改了对象状态后，其他线程能够看到发生的状态变化。
 * <p>
 * 可见性错误是指当读操作与写操作在不同的线程中执行时，我们无法确保执行读操作的线程能适时地看到其他线程写入的值，有时甚至是根本不可能的事情。
 * <p>
 * Java 提供了一种稍弱的同步机制，即 volatile 变量，用来确保将变量的更新操作通知到其他线程。可以将 volatile 看做一个轻量级的锁，但是又与锁有些不同：
 * - 对于多线程，不是一种互斥关系
 * - 不能保证变量状态的 "原子性操作"
 */
public class TestVolatile {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    /**
     * 有问题的示例: Thread 已经修改了flag，但是main线程还是拿到的false
     */
    public static void test1() {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while (true) {
            if (td.isFlag()) {
                System.out.println("______________");
                break;
            }
        }
    }

    /**
     * 加了锁，就可以让while循环每次都从主存中去读取数据，这样就能读取到true了。
     * 但是一加锁，每次只能有一个线程访问，当一个线程持有锁时，其他的就会阻塞，效率就非常低了。
     */
    public static void test2() {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while (true) {
            System.out.println("td:" + td.isFlag());
            synchronized (td) {
                if (td.isFlag()) {
                    System.out.println("______________");
                    break;
                }
            }
        }
    }

    /**
     * 不想加锁，又要解决内存可见性问题，那么就可以使用volatile关键字。
     */
    public static void test3() {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while (true) {
            if (td.isFlag()) {
                System.out.println("______________");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {
    //    private boolean flag = false;
    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            //增加这种出现问题的几率
            Thread.sleep(200);
        } catch (Exception e) {
        }
        flag = true;
        System.out.println("flag=" + isFlag());
    }
}
