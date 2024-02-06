package com.xx.simple.delay;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;

/**
 * 延迟任务实现方式一: DelayQueue 延迟队列
 */
@Slf4j
public class DelayQueueDemo {
    public static void main(String[] args) {
        DelayQueue<SanYouTask> sanYouTaskDelayQueue = new DelayQueue();
        new Thread(() -> {
            while (true) {
                try {
                    SanYouTask sanYouTask = sanYouTaskDelayQueue.take();
                    log.info("获取到延迟任务:{}", sanYouTask.getTaskContent());
                } catch (Exception e) {
                }
            }
        }).start();

        log.info("提交延迟任务");
        sanYouTaskDelayQueue.offer(new SanYouTask("三友的java日记5s", 5L));
        sanYouTaskDelayQueue.offer(new SanYouTask("三友的java日记3s", 3L));
        sanYouTaskDelayQueue.offer(new SanYouTask("三友的java日记8s", 8L));
    }
}
