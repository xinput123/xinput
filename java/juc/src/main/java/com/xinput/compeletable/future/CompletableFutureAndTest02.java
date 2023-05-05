package com.xinput.compeletable.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * {@link CompletableFuture} 组合 示例
 * <p>
 * thenCombine / thenAcceptBoth / runAfterBoth都表示：将两个CompletableFuture组合起来，
 * 只有这两个都正常执行完了，才会执行某个任务.
 * <p>
 * 区别在于:
 * - thenCombine：会将两个任务的执行结果作为方法入参，传递到指定方法中，且有返回值
 * - thenAcceptBoth: 会将两个任务的执行结果作为方法入参，传递到指定方法中，且无返回值
 * - runAfterBoth 不会把执行结果当做方法入参 ，且没有返回值。
 */
public class CompletableFutureAndTest02 {
  public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
    List<Integer> ids = new ArrayList();
    for (int i = 0; i < 10; i++) {
      ids.add(i);
    }

    long start = System.currentTimeMillis();
    CompletableFutureAndTest02 t = new CompletableFutureAndTest02();
    CompletableFuture<List<String>>[] futureArray = ids.stream()
        .map(data -> CompletableFuture
            .supplyAsync(() -> t.query(data))
            .whenComplete((result, th) -> {
            })).toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futureArray).join();

    for (CompletableFuture future : futureArray) {
      List<String> ss = (List<String>) future.get();
      for (String s : ss) {
        System.out.println(s);
      }
    }

    System.out.println("耗时: " + (System.currentTimeMillis() - start) / 1000L);
  }

  public List<String> query(int id) {
    List<String> s = new ArrayList();
    try {
      final int i = ThreadLocalRandom.current().nextInt(id + 1) + 1;
      System.out.println("计算: " + Thread.currentThread().getName() + ". " + i);
      TimeUnit.SECONDS.sleep(i);
      s.add(Thread.currentThread().getName() + " - " + id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return s;
  }
}
