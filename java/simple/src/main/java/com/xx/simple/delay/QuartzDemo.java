package com.xx.simple.delay;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * 延迟任务实现方式五: Quartz
 */
@Slf4j
public class QuartzDemo {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 1.创建Scheduler的工厂
        SchedulerFactory sf = new StdSchedulerFactory();
        // 2.从工厂中获取调度器实例
        Scheduler scheduler = sf.getScheduler();

        // 6.启动 调度器
        scheduler.start();

        // 3.创建JobDetail，Job类型就是上面说的SanYouJob
        JobDetail jb = JobBuilder.newJob(QuartzJob.class)
                .usingJobData("delayTask", "这是一个延迟任务")
                .build();

        // 4.创建Trigger
        Trigger t = TriggerBuilder.newTrigger()
                //任务的触发时间就是延迟任务到的延迟时间
                .startAt(new Date(System.currentTimeMillis() + 5000))
                .build();

        // 5.注册任务和定时器
        log.info("提交延迟任务");
        scheduler.scheduleJob(jb, t);
    }
}
