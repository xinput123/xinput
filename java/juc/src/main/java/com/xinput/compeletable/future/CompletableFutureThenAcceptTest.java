package com.xinput.compeletable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * {@link CompletableFuture} thenAccept 示例
 * <p>
 * CompletableFuture的thenAccept方法表示，第一个任务执行完成后，执行第二个回调方法任务，会将该任务的
 * 执行结果，作为入参，传递到回调方法中，但是回调方法是没有返回值 的。
 */
public class CompletableFutureThenAcceptTest {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
        () -> {
          System.out.println("原始CompletableFuture方法任务");
          return "xinput";
        }
    );

    CompletableFuture thenAcceptFuture = orgFuture.thenAccept((a) -> {
      if ("xinput".equals(a)) {
        System.out.println("关注了");
      }

      System.out.println("先考虑考虑");
    });

    System.out.println(thenAcceptFuture.get());
  }
}
