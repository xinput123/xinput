package com.xinput.compeletable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * {@link CompletableFuture} thenRun 示例
 * <p>
 * CompletableFuture的thenRun方法，通俗点讲就是，做完第一个任务后，再做第二个任务 。某个任务执行完成后，
 * 执行回调方法；但是前后两个任务没有参数传递，第二个任务也没有返回值
 */
public class CompletableFutureThenRunTest {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
        () -> {
          System.out.println("先执行第一个CompletableFuture方法任务");
          return "xinput";
        }
    );

    CompletableFuture thenRunFuture = orgFuture.thenRun(() -> {
      System.out.println("接着执行第二个任务");
    });

    System.out.println(orgFuture.get());
    System.out.println(thenRunFuture.get());
  }
}
