package com.xinput.volatiled;

/**
 * Lock 等待唤醒机制
 * 生产者消费者模型
 */
public class TestProductorAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);

        //没有等待唤醒机制的时候
        //生产者一直生产 不考虑消费者  可能造成数据丢失
        //消费者一直消费 不考虑生产者  可能造成重复消费
        new Thread(productor, "生产者a").start();
        new Thread(consumer, "消费者a").start();
    }
}

/**
 * 店员
 */
class Clerk {
    //库存共享数据 存在安全问题
    private int product = 0;

    //进货
    public synchronized void get() {
        if (product >= 10) {
            System.out.println("产品已满，无法添加");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        } else {
            this.notifyAll();
            System.out.println(Thread.currentThread().getName() + "店员进货1个产品 库存为" + ++product);
        }
    }

    //卖货
    public synchronized void sale() {
        if (product <= 0) {
            System.out.println("产品缺货，无法售卖");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "店员销售1个产品 库存为" + --product);
            this.notifyAll();
        }
    }
}

/**
 * 生产者
 */
class Productor implements Runnable {
    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.get();
        }
    }
}

/**
 * @Description 消费者
 */
class Consumer implements Runnable {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}
