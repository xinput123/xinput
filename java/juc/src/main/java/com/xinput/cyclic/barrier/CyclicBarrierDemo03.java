package com.xinput.cyclic.barrier;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier: 字面意思回环栅栏。通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 通过CyclicBarrier的await()方法，线程就处于barrier状态。
 * <p>
 * 示例：抱团旅游
 * 出发时，导游会在机场收了护照和签证，办理集体出境手续，所以，要等大家都到齐才能出发，
 * 出发前再把护照和签证发到大家手里。
 * 对应CyclicBarrier使用。
 * 每个人到达后进入barrier状态。
 * 都到达后，唤起大家一起出发去旅行。
 * 旅行出发前，导游还会有个发护照和签证的动作。
 */
public class CyclicBarrierDemo03 {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new TourGuideTask());
        Executor executor = Executors.newFixedThreadPool(3);
        //哈登最大牌，到的最晚
        executor.execute(new TravelTask(cyclicBarrier, "哈登", 5));
        executor.execute(new TravelTask(cyclicBarrier, "保罗", 3));
        executor.execute(new TravelTask(cyclicBarrier, "戈登", 1));
    }
}
