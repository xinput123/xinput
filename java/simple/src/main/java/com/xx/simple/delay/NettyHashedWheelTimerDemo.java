package com.xx.simple.delay;

import io.netty.util.HashedWheelTimer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 延迟任务实现方式四: NettyHashedWheelTimer
 */
@Slf4j
public class NettyHashedWheelTimerDemo {
    public static void main(String[] args) {
        HashedWheelTimer timer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 8);
        timer.start();

        log.info("提交延迟任务");
        timer.newTimeout(timeout -> log.info("执行延迟任务"), 5, TimeUnit.SECONDS);
    }
}
