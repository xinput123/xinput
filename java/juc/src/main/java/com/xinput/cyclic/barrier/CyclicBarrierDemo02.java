package com.xinput.cyclic.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier: 一组线程之前相互等待
 * 允许一组线程相互等待，达到一个共同点(Barrier,栅栏),再继续执行
 */
public class CyclicBarrierDemo02 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        final CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("玩家准备好，游戏开始");
            }
        });
        playerWait(barrier, executor);
        /**
         * 可重用
         */
        playerWait(barrier, executor);
        playerWait(barrier, executor);
    }

    public static void playerWait(final CyclicBarrier barrier, ExecutorService executor) {
        for (int i = 0; i < 5; i++) {
            final int j = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("玩家" + j + "准备好，等待其他玩家准备");
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
