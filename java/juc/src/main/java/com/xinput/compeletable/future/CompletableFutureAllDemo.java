package com.xinput.compeletable.future;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 等待所有异步任务返回，并取出所有的返回结果
 */
public class CompletableFutureAllDemo {

    //开启多线程
    public static ExecutorService exs = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

//        System.out.println("=====SyncBegin=====");
//        sync();
//        System.out.println("=====SyncEnd=====");
//        System.out.println("=====AsyncBegin=====");
//        async();
//        System.out.println("=====AsyncEnd=====");
        System.out.println("=====Async2Begin=====");
        async2();
        System.out.println("=====Async2End=====");
    }

    // 这种方式更加简单一点
    public static void async2() {
        int max = 20;
        long start = System.currentTimeMillis();

        // 模拟包含多个数据查询的集合
        List<CompletableFuture<String>> completableFutures = Lists.newArrayListWithCapacity(max * 2);

        IntStream.range(1, max + 1).forEach(num -> {
            completableFutures.add(CompletableFuture.supplyAsync(() -> findSomeValue2(num), exs));
        });

        // 使用CompletableFuture.allOf()等待所有数据查询成功
//        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
//                completableFutures.toArray(new CompletableFuture[completableFutures.size()]));

        // 在所有数据查询成功后执行操作
//        allFutures.thenRun(() -> {
//            System.out.println("All queries completed successfully!");
//        });

        // 在所有数据查询成功后获取查询结果
        List<String> results = completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        System.out.println("Query results: " + results);

        System.out.println("耗时: " + (System.currentTimeMillis() - start));
    }

    public static void async() {
        int max = 20;
        List<CompletableFuture<String>> list = Lists.newArrayListWithCapacity(max * 2);

        long start = System.currentTimeMillis();
        IntStream.range(1, max + 1).forEach(num -> {
            list.add(findSomeValueAsync(num));
        });

        CompletableFuture<Void> allfuture = CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]));//Created All of object
        CompletableFuture<List<String>> allFutureList = allfuture.thenApplyAsync(val -> {
            return list.stream().map(f -> f.join()).collect(Collectors.toList());
        }, exs);

        CompletableFuture<List<String>> futureHavingAllValues =
                allFutureList.thenApply(fn -> fn.stream().collect(Collectors.toList()));

        List<String> concatenateString = futureHavingAllValues.join();
//    concatenateString.forEach(System.out::println);
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
    }

    public static void sync() {
        int max = 20;
        List<CompletableFuture<String>> list = Lists.newArrayListWithCapacity(max * 2);

        long start = System.currentTimeMillis();
        IntStream.range(1, max + 1).forEach(num -> {
            list.add(findSomeValue(num));
        });

        CompletableFuture<Void> allfuture = CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]));//Created All of object
        CompletableFuture<List<String>> allFutureList = allfuture.thenApply(val -> {
            return list.stream().map(f -> f.join()).collect(Collectors.toList());
        });

        CompletableFuture<List<String>> futureHavingAllValues = allFutureList.thenApply(fn -> {
            return fn.stream().collect(Collectors.toList());
        });

        List<String> concatenateString = futureHavingAllValues.join();
//    concatenateString.forEach(System.out::println);
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
    }

    public static CompletableFuture<String> findSomeValueAsync(int i) {
        return CompletableFuture.supplyAsync(() -> {
            int sleep = 3 + i / 5;
            System.out.println("任务: " + i + " ,sleep: " + sleep + " begin");
            sleep(sleep);
            return "任务: " + i + " ,sleep: " + sleep;
        }, exs);
    }

    public static CompletableFuture<String> findSomeValue(int i) {
        return CompletableFuture.supplyAsync(() -> {
            int sleep = 3 + i / 5;
            System.out.println("任务: " + i + " ,sleep: " + sleep + " begin");
            sleep(sleep);
            return "任务: " + i + " ,sleep: " + sleep;
        });
    }

    public static String findSomeValue2(int i) {
        int sleep = 3 + i / 5;
        System.out.println("任务: " + i + " ,sleep: " + sleep + " begin");
        sleep(sleep);
        return "任务: " + i + " ,sleep: " + sleep;
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
