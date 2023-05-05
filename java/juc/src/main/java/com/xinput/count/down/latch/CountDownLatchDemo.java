package com.xinput.count.down.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * {@link CountDownLatch} 示例
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @since
 */
public class CountDownLatchDemo {

  private static CountDownLatch countDownLatch = new CountDownLatch(5);

  public static void main(String[] args) throws InterruptedException {
    // 创建定长线程池
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    for (int i = 1; i <= 5; i++) {
      executorService.execute(() -> {
        int sleepSecond = ThreadLocalRandom.current().nextInt(10) + 1;
        String name = Thread.currentThread().getName();
        System.out.println(name + ":我要" + sleepSecond + "秒才能完成任务!");
        try {
          TimeUnit.SECONDS.sleep(sleepSecond);
          System.out.println(name + "完成任务啦!");
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          // 倒计时减一
          // 注.countDown()方法是线程安全的
          countDownLatch.countDown();
        }
      });
    }

    // 当countDownLatch的值不为0时,此线程阻塞，继续等待;直到countDownLatch的值=0时,此线程才往下执行
    // 这里是.await()方法,而不是.wait()方法
    countDownLatch.await();
    // 关闭线程池
    executorService.shutdown();
    System.out.println(Thread.currentThread().getName() + ":终于该本线程出手了!");
  }
}
