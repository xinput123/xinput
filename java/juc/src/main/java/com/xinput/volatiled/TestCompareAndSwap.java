package com.xinput.volatiled;

/**
 * 解决了原子性问题，解决了内存可见性的问题
 * <p>
 * CAS (Compare-And-Swap) 是一种硬件对并发的支持，针对多处理器操作而设计的处理器中的一种特殊指令，用于管理对共享数据的并发访问。CAS 是一种无锁的非阻塞算法的实现。
 * <p>
 * CAS 包含了 3 个操作数：
 * - 需要读写的内存值 V 进行比较的值 A 拟写入的新值 B
 * - 当且仅当 V 的值等于 A 时， CAS 通过原子方式用新值 B 来更新 V 的值，否则不会执行任何操作。
 * - CAS比较失败的时候不会放弃CPU，会反复执行，直到自己修改主内存的数据
 */
public class TestCompareAndSwap {
    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectValue = cas.getValue();
                    System.out.println(cas.compareAndSet(expectValue, (int) Math.random() * 101));
                }
            }).start();
        }
    }
}

class CompareAndSwap {
    public int value;

    //获取内存值
    public synchronized int getValue() {
        return value;
    }

    //比较并交换
    public synchronized int compareAndSwap(int expectValue, int newV) {
        int oldV = value;
        //内存值和预估值一致 就替换
        if (oldV == expectValue) {
            this.value = newV;
        }
        return oldV;
    }

    //设置 调用比较并交换  看期望值和原来的值是否一致
    public synchronized boolean compareAndSet(int expectValue, int newV) {
        return expectValue == compareAndSwap(expectValue, newV);
    }
}
