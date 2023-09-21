package com.xinput.count.down.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo03 {

    // 线程核心数
    public final static int CORE_PROCESS = Runtime.getRuntime().availableProcessors();

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            1,
            1,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private static int count = 10;

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            int finalI = i;
            executor.execute(() -> {
                print(finalI + "开始");
                sleep(finalI);
                latch.countDown();
                print(finalI + "结束");
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("全部执行完成");
    }

    public static void sleep(int i) {
        try {
            print(i + "任务任务");
            TimeUnit.SECONDS.sleep(3);
            print(i + "任务完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void print(String name) {
        System.out.println(Thread.currentThread().getName() + ": " + name);
    }
}
