package com.xinput.compeletable.futureV1;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.xinput.util.Factory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * 20个使用 Java CompletableFuture的例子
 * https://colobu.com/2018/03/12/20-Examples-of-Using-Java%E2%80%99s-CompletableFuture/
 */
public class CompletableFutureV1001 {
    static ExecutorService executor = Executors.newFixedThreadPool(5, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    static String delayedUpperCase(String mesage) {
        return delayedUpperCase(5, mesage);
    }

    static String delayedUpperCase(int second, String mesage) {
        Factory.sleep(second);
        return mesage.toUpperCase();
    }

    static String delayedLowerCase(String mesage) {
        return delayedLowerCase(3, mesage);
    }

    static String delayedLowerCase(int second, String mesage) {
        Factory.sleep(second);
        return mesage.toLowerCase();
    }

    // 1、创建一个完成的CompletableFuture
    static void completedFutureExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        // getNow(null)方法在future完成的情况下会返回结果，就比如上面这个例子，否则返回null (传入的参数)
        assertEquals("message", cf.getNow(null));
    }

    // 2、运行一个简单的异步阶段
    static void runAsyncExample() {
        // CompletableFuture的方法如果以Async结尾，它会异步的执行(没有指定executor的情况下)，
        // 异步执行通过ForkJoinPool实现， 它使用守护线程去执行任务。
        // 注意这是CompletableFuture的特性， 其它CompletionStage可以override这个默认的行为。
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            Factory.sleep(2);
        });
        assertFalse(cf.isDone());
        Factory.sleep(3);
        assertTrue(cf.isDone());
    }

    // 3、在前一个阶段上应用函数
    static void thenApplyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message")
                // then意味着这个阶段的动作发生当前的阶段正常完成之后。 本例中，当前节点完成，返回字符串message。
                // Apply意味着返回的阶段将会对结果前一阶段的结果应用一个函数。
                .thenApply(s -> {
                    assertFalse(Thread.currentThread().isDaemon());
                    return s.toUpperCase();
                });
        assertEquals("MESSAGE", cf.getNow(null));
    }

    // 4、在前一个阶段上异步应用函数
    static void thenApplyAsyncExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(s -> {
                    assertTrue(Thread.currentThread().isDaemon());
                    Factory.sleep(5);
                    return s.toUpperCase();
                });
        assertNull(cf.getNow(null));
        System.out.println(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
        System.out.println(cf.join());
    }

    // 5、使用定制的Executor在前一个阶段上异步应用函数
    static void thenApplyAsyncWithExecutorExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
            assertFalse(Thread.currentThread().isDaemon());
            Factory.sleep(3);
            return s.toUpperCase();
        }, executor);

        assertNull(cf.getNow(null));
        System.out.println(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
        System.out.println(cf.join());
    }

    // 6、消费前一阶段的结果
    static void thenAcceptExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept message")
                .thenAccept(s -> result.append(s));
        assertTrue("Result was empty", result.length() > 0);
    }

    // 7、异步地消费迁移阶段的结果
    static void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s -> result.append(s));
        cf.join();
        assertTrue("Result was empty", result.length() > 0);
    }

    // 8、完成计算异常
    static void completeExceptionallyExample() {
//        // delayedExecutor(timeout, timeUnit)延迟执行一个异步任务， 是 jdk9 里面提供的
//        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
//                CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
//        CompletableFuture exceptionHandler = cf.handle((s, th) -> {
//            return (th != null) ? "message upon cancel" : "";
//        });
//        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
//        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
//        try {
//            cf.join();
//            fail("Should have thrown an exception");
//        } catch (CompletionException ex) { // just for testing
//            assertEquals("completed exceptionally", ex.getCause().getMessage());
//        }
//
//        assertEquals("message upon cancel", exceptionHandler.join());
    }

    // 9、取消计算
    static void cancelExample() {
//        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
//                CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
//        CompletableFuture cf2 = cf.exceptionally(throwable -> "canceled message");
//        assertTrue("Was not canceled", cf.cancel(true));
//        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
//        assertEquals("canceled message", cf2.join());
    }

    // 10、在两个完成的阶段其中之一上应用函数
    // applyToEither: 表示 cf1 和 cf2 任意一个完成即执行下一步操作
    // cf1 字符串转大写， cf2 字符串转小写
    static void applyToEitherExample() {
        String original = "Message";

        // 这里 cf2 先执行完，而 cf1 尚未执行完
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(5, s));
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedLowerCase(3, s));
        CompletableFuture<String> cf = cf1.applyToEither(
                cf2,
                s -> s + " from applyToEither");

        assertEquals(cf.join(), "message from applyToEither");
        assertFalse(cf1.isDone());
        assertTrue(cf2.isDone());


        // 这里 cf1 先执行完，而 cf2 尚未执行完
        cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(3, s));
        cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedLowerCase(5, s));
        cf = cf1.applyToEither(
                cf2,
                s -> s + " from applyToEither");

        assertEquals(cf.join(), "MESSAGE from applyToEither");
        assertTrue(cf1.isDone());
        assertFalse(cf2.isDone());
    }

    // 11、在两个完成的阶段其中之一上调用消费函数
    // 11 和 10 的区别是这里使用的 Consumer 函数，且没有返回值
    static void acceptEitherExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(3, s));
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedLowerCase(5, s));

        CompletableFuture cf = cf1.acceptEither(cf2, s -> result.append(s).append(" acceptEither"));
        cf.join();
        assertEquals(result.toString(), "MESSAGE acceptEither");
    }

    // 12、在两个阶段都执行完后运行一个 Runnable
    static void runAfterBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> {
                    String s1 = delayedUpperCase(5, s);
                    result.append(s1).append(" ");
                    return s1;
                });
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> {
                    String s2 = delayedLowerCase(3, s);
                    result.append(s2).append(" ");
                    return s2;
                });

        CompletableFuture<Void> cf = cf1.runAfterBoth(cf2, () -> result.append("done"));
        cf.join();

        assertTrue(cf.isDone());
        assertTrue(cf1.isDone());
        assertTrue(cf2.isDone());
        assertEquals("message MESSAGE done", result.toString());
    }

    // 13、 使用BiConsumer处理两个阶段的结果
    static void thenAcceptBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(5, s));
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedLowerCase(3, s));
        CompletableFuture<Void> cf = cf1.thenAcceptBoth(cf2, (s1, s2) -> result.append(s1).append(" ").append(s2));
        cf.join();
        assertTrue(cf.isDone());
        assertTrue(cf1.isDone());
        assertTrue(cf2.isDone());
        assertEquals("MESSAGE message", result.toString());
    }

    // 14、使用BiFunction 同步处理两个阶段的结果
    // 如果CompletableFuture依赖两个前面阶段的结果， 它复合两个阶段的结果再返回一个结果，
    // 我们就可以使用thenCombine()函数。整个流水线是同步的，所以getNow()会得到最终的结果，
    // 它把大写和小写字符串连接起来。
    static void thenCombineExample() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApply(s -> delayedUpperCase(5, s));
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApply(s -> delayedLowerCase(3, s));

        CompletableFuture<String> cf = cf1.thenCombine(cf2, (s1, s2) -> s1 + s2);
        assertTrue(cf.isDone());
        assertTrue(cf1.isDone());
        assertTrue(cf2.isDone());
        assertEquals("MESSAGEmessage", cf.getNow(null));
        stopwatch.stop();
        System.out.println(stopwatch.elapsed().getSeconds());
    }

    // 15、异步使用BiFunction处理两个阶段的结果
    // 和 14 thenCombineExample 例子相似，但是有一点不同：依赖的前两个阶段异步地执行，所以 thenCombine() 也异步的执行，
    // 即便它没有 Async 后缀，所以我们需要 join 方法等得结果的完成
    static void thenCombineAsyncExample() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(5, s), executor);
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedLowerCase(3, s), executor);

        /**
         * Actions supplied for dependent completions of
         *  non-async methods may be performed by the thread that
         *  completes the current CompletableFuture, or by any other caller of
         *  a completion method
         */
        CompletableFuture<String> cf = cf1.thenCombine(cf2, (s1, s2) -> s1 + s2);
//        CompletableFuture<String> cf = cf1.thenCombineAsync(cf2, ((s1, s2) -> s1 + s2), executor);
        String result = cf.join();
        assertTrue(cf.isDone());
        assertTrue(cf1.isDone());
        assertTrue(cf2.isDone());
        assertEquals("MESSAGEmessage", result);
        stopwatch.stop();
        System.out.println(stopwatch.elapsed().getSeconds());
    }

    // 16、组合 CompletableFuture
    // 功能和 14，15 一样
    // 等待第一个阶段的完成(大写转换)， 它的结果传给一个指定的返回CompletableFuture函数，它的结果就是返回的CompletableFuture的结果
    static void thenComposeExample() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(5, s), executor);
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedLowerCase(3, s), executor);

        // 函数需要一个大写字符串做参数，然后返回一个 CompletableFuture,
        // 这个 CompletableFuture 会转换字符串变成小写然后连接在大写字符串的后面。
        CompletableFuture<String> cf = cf1.thenCompose(upper -> cf2.thenApply(s -> upper + s));
        String result = cf.join();
        assertTrue(cf.isDone());
        assertTrue(cf1.isDone());
        assertTrue(cf2.isDone());
        assertEquals("MESSAGEmessage", result);
        stopwatch.stop();
        System.out.println(stopwatch.elapsed().getSeconds());
    }

    // 17、当几个阶段中的任意一个完成，创建一个完成的阶段
    static void anyOfExample() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture<String>> futures = Lists.newArrayListWithCapacity(messages.size());
        for (int i = 0; i < messages.size(); i++) {
            String msg = messages.get(i);
            CompletableFuture<String> cf = CompletableFuture.completedFuture(msg)
                    .thenApplyAsync(s -> delayedUpperCase(Factory.getRandom(5), msg), executor);
            futures.add(cf);
        }

        CompletableFuture<Object> cf = CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, throwable) -> {
            if (throwable == null) {
                result.append(res);
            }
        });

        cf.join();
        System.out.println("================");
        for (CompletableFuture<String> future : futures) {
            if (future.isDone()) {
                System.out.println(future.join());
            }
        }
        System.out.println("=================");
        System.out.println(result.toString()); // 随机的

        stopwatch.stop();
        System.out.println(stopwatch.elapsed().getSeconds());
    }

    // 18、当所有的阶段都完成后创建一个阶段
    // 当所有的阶段完成后才继续处理, 同步方式
    static void allOfExample() {
        Stopwatch stopwatch = Stopwatch.createStarted();

        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());

        CompletableFuture<Void> cf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {
            futures.forEach(future -> {
                System.out.println(future.getNow(null));
            });
            result.append("done");
        });
        System.out.println(cf.isDone());
//        assertTrue("Result was empty", result.length() > 0);
        System.out.println(result.toString());
        stopwatch.stop();
        System.out.println("cost: " + stopwatch.elapsed().getSeconds());
    }

    // 19、当所有的阶段都完成后创建一个阶段
    // 当所有的阶段完成后才继续处理, 异步方式
    static void allOfAsyncExample() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture<String>> futures = Lists.newArrayListWithCapacity(messages.size());
        for (int i = 0; i < messages.size(); i++) {
            String msg = messages.get(i);
            CompletableFuture<String> cf = CompletableFuture.completedFuture(msg)
                    .thenApplyAsync(s -> delayedUpperCase(Factory.getRandom(5), msg), executor);
            futures.add(cf);
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        List<String> results = Lists.newArrayListWithCapacity(messages.size());
        allFutures.whenComplete((v, th) -> {
            futures.forEach(cf -> results.add(cf.join()));
        });
        allFutures.join();
        System.out.println("result 2:" + results);

        stopwatch.stop();
        System.out.println("cost: " + stopwatch.elapsed().getSeconds());
    }

    public static void main(String[] args) {
//        completedFutureExample();
//        runAsyncExample();
//        thenApplyExample();
//        thenApplyAsyncExample();
//        thenApplyAsyncWithExecutorExample();
//        thenAcceptExample();
//        thenAcceptAsyncExample();
//        completeExceptionallyExample();
//        applyToEitherExample();
//        acceptEitherExample();
//        runAfterBothExample();
//        thenAcceptBothExample();
//        thenCombineExample();
//        thenCombineAsyncExample();
//        thenComposeExample();
//        anyOfExample();
//        allOfExample();
        allOfAsyncExample();
    }
}