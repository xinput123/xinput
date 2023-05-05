package com.xinput.cyclic.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * {@link CyclicBarrier} 示例
 */
public class CyclicBarrierDemo {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    for (int i = 1; i <= 20; i++) {
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
          try {
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + "业务逻辑执行完毕");
          } catch (InterruptedException e) {
            e.printStackTrace();
          } catch (BrokenBarrierException e) {
            e.printStackTrace();
          }
        }
      });
    }
    executorService.shutdown();
  }
}
