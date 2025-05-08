package com.xx.simple.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtils {

    public static final ExecutorService executorService =
            new ThreadPoolExecutor(
                    // 核心线程数
                    8,
                    // 最大线程数
                    10,
                    // 空闲回收时间
                    30,
                    // 空闲回收时间单位
                    TimeUnit.SECONDS,
                    // 提交任务的队列，当线程数量超过核心线程数时，可以将任务提交对队列中
                    new ArrayBlockingQueue(500),
                    // 线程工厂，可以自定义线程的一些属性
                    new ThreadPoolExecutor.AbortPolicy()
            );
    /**
     * 队列和最大线程数都满了处理策略
     * ThreadPoolExecutor.AbortPolicy: 丢弃任务并抛出 RejectedExecutionException 异常
     * ThreadPoolExecutor.DiscardPolicy: 丢弃任务但是不抛出异常
     * ThreadPoolExecutor.DiscardOldestPolicy: 丢弃队列最前面的任务，然后重新尝试此任务(重复此过程)
     * ThreadPoolExecutor.CallerRunsPolicy: 由调用线程处理该任务
     */
}
