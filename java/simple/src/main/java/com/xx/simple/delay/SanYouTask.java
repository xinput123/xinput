package com.xx.simple.delay;

import lombok.Getter;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * getDelay(): 返回这个任务还剩多久时间可以执行，小于0的时候说明可以这个延迟任务到了执行的时间了。
 * compareTo(): 这个是对任务排序的，保证最先到延迟时间的任务排到队列的头。
 */
@Getter
public class SanYouTask implements Delayed {

    private final String taskContent;
    private final Long triggerTime;

    public SanYouTask(String taskContent, Long delayTime) {
        this.taskContent = taskContent;
        this.triggerTime = System.currentTimeMillis() + delayTime * 1000;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(triggerTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return this.triggerTime.compareTo(((SanYouTask) o).triggerTime);
    }
}
