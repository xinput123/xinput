package com.xinput.util;

import com.google.common.collect.Lists;
import com.xinput.entity.Taskinfo;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @since
 */
public class Factory {
    public static void sleep(int second) {
        try {
//            System.out.println("Thread: " + Thread.currentThread().getName() + " sleep: " + second);
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getRandom(int max) {
        if (max < 1) {
            return 1;
        }
        return ThreadLocalRandom.current().nextInt(1, max);
    }

    public static Taskinfo createTask(int taskId) {
        return new Taskinfo(taskId, getRandom(5));
    }

    public static List<Taskinfo> createTaskList(int num) {
        if (num <= 0) {
            return Lists.newArrayList();
        }

        List<Taskinfo> taskinfos = Lists.newArrayListWithCapacity(num);
        IntStream.range(1, num + 1).forEach(taskId -> taskinfos.add(createTask(taskId)));
        return taskinfos;
    }
}
