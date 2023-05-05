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
public class CompletableFutureAllDemo2 {

    //开启多线程
    public static ExecutorService exs = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        int max = 20;
        // 模拟包含多个数据查询的集合
        List<CompletableFuture<String>> completableFutures = Lists.newArrayListWithCapacity(max * 2);

        IntStream.range(1, max + 1).forEach(num -> {
            completableFutures.add(CompletableFuture.supplyAsync(() -> findSomeValue2(num), exs));
        });

        // 在所有数据查询成功后获取查询结果
        List<String> results = completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        System.out.println("Query results: " + results);
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
