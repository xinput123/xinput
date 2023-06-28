package com.xx;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * @since
 */
public class MemoryMXBeanDemo {

    public static void main(String[] args) {
        logMemory();
    }

    static void logMemory() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        //堆内存使用情况
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
        //初始的总内存
        long totalMemorySize = memoryUsage.getInit();
        //已使用的内存
        long usedMemorySize = memoryUsage.getUsed();

        System.out.println("Total Memory: " + totalMemorySize / (1024 * 1024) + " Mb");
        System.out.println("Free Memory: " + usedMemorySize / (1024 * 1024) + " Mb");
    }
}
