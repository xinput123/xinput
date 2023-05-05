package com.xinput.compeletable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * OR组合关系
 * {@link CompletableFuture} OR组合 示例
 * <p>
 * applyToEither / acceptEither / runAfterEither 都表示：将两个CompletableFuture组合起来，只要其中一个执行完了,
 * 就会执行某个任务。
 * <p>
 * 区别在于:
 * - applyToEither：会将已经执行完成的任务，作为方法入参，传递到指定方法中，且有返回值
 * - acceptEither: 会将已经执行完成的任务，作为方法入参，传递到指定方法中，且无返回值
 * - runAfterEither：不会把执行结果当做方法入参，且没有返回值。
 */
public class CompletableFutureOrTest {
  public static void main(String[] args) {
    //第一个异步任务，休眠2秒，保证它执行晚点
    CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
      try {

        Thread.sleep(2000L);
        System.out.println("执行完第一个异步任务");
      } catch (Exception e) {
        return "第一个任务异常";
      }
      return "第一个异步任务";
    });
    ExecutorService executor = Executors.newSingleThreadExecutor();
    CompletableFuture<Void> future = CompletableFuture
        //第二个异步任务
        .supplyAsync(() -> {
              System.out.println("执行完第二个任务");
              return "第二个任务";
            }
            , executor)
        //第三个任务
        .acceptEitherAsync(first, System.out::println, executor);

    executor.shutdown();
  }
}
