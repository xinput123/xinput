package com.xinput.compeletable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * {@link CompletableFuture} whenComplete 示例
 * <p>
 * CompletableFuture的whenComplete方法表示，某个任务执行完成后，执行的回调方法，无返回值;
 * 并且whenComplete方法返回的CompletableFuture的result是上个任务的结果 。
 */
public class CompletableFutureWhenCompleteTest {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
        () -> {
          System.out.println("当前线程名称：" + Thread.currentThread().getName());
          try {
            Thread.sleep(2000L);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          return "xinput";
        }
    );

    CompletableFuture<String> rstFuture = orgFuture.whenComplete((a, throwable) -> {
      System.out.println("当前线程名称：" + Thread.currentThread().getName());
      System.out.println("上个任务执行完啦，还把" + a + "传过来");
      if ("xinput".equals(a)) {
        System.out.println("666");
      }
      System.out.println("233333");
    });

    System.out.println(rstFuture.get());
  }
}
