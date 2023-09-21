package com.xinput.count.down.latch;

import com.xinput.util.Factory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch: 一个线程等待多个线程
 * 允许一个或多个线程等待一组线程完成后，再继续执行
 * 其实这从 CountDownLatch 的名字也可以大概看出它的用途 count:计数 down:减 latch:门栓
 * 当减数器减到0时，门栓就打开了，线程可以继续执行
 */
public class CountDownLatchDemo02 {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) {
        executorService.execute(() -> {
            try {
                System.out.println("等待所有人准备好...");
                // await() : 在当前线程在锁存器倒计数至零之前一直等待
                // await(long timeout, TimeUnit unit) throws InterruptedException { };
                // 和await()类似，只不过等待timeout的时间后计数器值还没变为0的话就会继续执行
                countDownLatch.await();
                System.out.println("所有人都准备好了，预备");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            System.out.println("玩家1正在准备...");
            Factory.sleep(3);
            System.out.println("玩家1准备好了...");
            // countDown() 递减锁存器的计数，如果计数到达零，则释放所有等待的线程
            countDownLatch.countDown();
            System.out.println("玩家1 " + countDownLatch.getCount());
        });

        executorService.execute(() -> {
            System.out.println("玩家2正在准备...");
            Factory.sleep(2);
            System.out.println("玩家2准备好了...");
            countDownLatch.countDown();
            System.out.println("玩家2 " + countDownLatch.getCount());
        });

        executorService.execute(() -> {
            System.out.println("玩家3正在准备...");
            Factory.sleep(5);
            System.out.println("玩家3准备好了...");
            countDownLatch.countDown();
            System.out.println("玩家3 " + countDownLatch.getCount());
        });
    }
}
