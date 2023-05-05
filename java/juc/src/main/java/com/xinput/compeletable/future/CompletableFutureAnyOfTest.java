package com.xinput.compeletable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * {@link CompletableFuture} anyOf 示例
 * <p>
 * 任意一个任务执行完，就执行anyOf返回的CompletableFuture。如果执行的任务异常，anyOf的CompletableFuture，
 * 执行get方法，会抛出异常
 */
public class CompletableFutureAnyOfTest {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    CompletableFuture<Void> a = CompletableFuture.runAsync(() -> {
      try {
        Thread.sleep(3000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("我执行完了");
    });
    CompletableFuture<Void> b = CompletableFuture.runAsync(() -> {
      System.out.println("我也执行完了");
    });
    CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(a, b).whenComplete((m, k) -> {
      System.out.println("finish");
    });
    anyOfFuture.join();
  }
}
