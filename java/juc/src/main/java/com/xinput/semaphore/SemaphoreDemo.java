package com.xinput.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * {@link Semaphore} 示例
 * 阻塞
 * acquire(): 用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许可
 * acquire(int permits):获取 permits 个许可
 * release(): 释放许可
 * release(int permits) : 释放 permits 个许可。
 * <p>
 * 非阻塞
 * tryAcquire():尝试获取一个许可，若获取成功，则立即返回 true，若获取失败，则立即返回 false。
 * tryAcquire(long timeout, TimeUnit unit):尝试获取一个许可，若在指定的时间内获取成功，则立即返回 true，否则则立即返回 false。
 * tryAcquire(int permits):尝试获取 permits 个许可，若获取成功，则立即返回 true，若获取失败，则立即返回 false。
 * tryAcquire(int permits, long timeout, TimeUnit unit): 尝试获取 permits个许可，若在指定的时间内获取成功，则立即返回 true，否则则立即返回 false。
 */
public class SemaphoreDemo {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    // 获取型号量实例(设置允许最大线程数为20个)
    Semaphore semaphore = new Semaphore(20);
    for (int i = 1; i <= 100; i++) {
      executorService.execute(() -> {
        try {
          // 申请攀岩绳索(即:获取资格)  -> 如果没有获取到资格那么就一直等下去,直到获取到了资格为止
          semaphore.acquire();
          int sleepSecond = ThreadLocalRandom.current().nextInt(10) + 1;
          String name = Thread.currentThread().getName();
          System.out.println(name + "获取了攀岩资格，开始攀岩! 剩余许可: " + semaphore.availablePermits());
          TimeUnit.SECONDS.sleep(sleepSecond);
          System.out.println(name + "攀岩结束！ 剩余许可: " + semaphore.availablePermits());
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          // 让出绳索(即:释放资格)
          semaphore.release();
        }
      });
    }
  }
}
