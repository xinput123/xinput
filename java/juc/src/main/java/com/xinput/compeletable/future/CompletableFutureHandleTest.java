package com.xinput.compeletable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

/**
 * {@link CompletableFuture} handle 示例
 * <p>
 * CompletableFuture的handle方法表示，某个任务执行完成后，执行回调方法，并且是有返回值的；
 * 并且handle方法返回的CompletableFuture的result是回调方法 执行的结果。
 */
public class CompletableFutureHandleTest {
  public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

    CompletableFuture<String> first = CompletableFuture.completedFuture("第一个异步任务");
    ExecutorService executor = Executors.newFixedThreadPool(10);
    CompletableFuture<String> future = CompletableFuture
        // 第二个异步任务
        .supplyAsync(() -> "第二个异步任务", executor)
        // (w, s) -> System.out.println(s) 是第三个任务
        .thenCombineAsync(first, (s, w) -> {
          System.out.println(w);
          System.out.println(s);
          return "两个异步任务的组合";
        }, executor);
    System.out.println(future.join());
    executor.shutdown();

  }
}
