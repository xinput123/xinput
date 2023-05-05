package com.xinput.compeletable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * {@link CompletableFuture} allOf 示例
 * <p>
 * 所有任务都执行完成后，才执行 allOf返回的CompletableFuture。如果任意一个任务异常，allOf的CompletableFuture，
 * 执行get方法，会抛出异常
 * <p>
 * 区别在于:
 * - applyToEither：会将已经执行完成的任务，作为方法入参，传递到指定方法中，且有返回值
 * - acceptEither: 会将已经执行完成的任务，作为方法入参，传递到指定方法中，且无返回值
 * - runAfterEither：不会把执行结果当做方法入参，且没有返回值。
 */
public class CompletableFutureAllOfTest {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    CompletableFuture<Void> a = CompletableFuture.runAsync(() -> {
      System.out.println("我执行完了");
    });
    CompletableFuture<Void> b = CompletableFuture.runAsync(() -> {
      System.out.println("我也执行完了");
    });
    CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(a, b).whenComplete((m, k) -> {
      System.out.println("finish");
    });
  }
}
