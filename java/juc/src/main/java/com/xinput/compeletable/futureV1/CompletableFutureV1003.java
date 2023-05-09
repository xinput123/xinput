package com.xinput.compeletable.futureV1;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.xinput.entity.OrderDetailDTO;
import com.xinput.entity.OrderInfo;
import com.xinput.entity.UserInfo;
import com.xinput.util.Factory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @since
 */
public class CompletableFutureV1003 {
    public static ThreadFactory guavaThreadFactory =
            new ThreadFactoryBuilder().setNameFormat("CompletableFutureV1003-").build();

    public static ExecutorService executor = new ThreadPoolExecutor(
            10,
            20,
            1L,
            TimeUnit.MINUTES,
            new SynchronousQueue<>(),
            guavaThreadFactory,
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        System.out.println(createOrderDetailDTO(10006L, "100000001"));
    }

    /**
     * 模拟从两个不同的服务获取信息，并组装在一起
     *
     * @param userId  用户id
     * @param orderId 订单信息
     * @return
     */
    static OrderDetailDTO createOrderDetailDTO(Long userId, String orderId) {
        Stopwatch sw = Stopwatch.createStarted();
        CompletableFuture<UserInfo> userInfoCompletableFuture =
                CompletableFuture.supplyAsync(() -> getUserInfo(userId), executor);
        CompletableFuture<OrderInfo> orderInfoCompletableFuture =
                CompletableFuture.supplyAsync(() -> getOrderInfo(orderId), executor);
        
        CompletableFuture<OrderDetailDTO> result = userInfoCompletableFuture.thenCombineAsync(orderInfoCompletableFuture, (userInfo, order) -> {
            return new OrderDetailDTO(userInfo, order);
        }, executor);
        OrderDetailDTO orderDetailDTO = result.join();

        sw.stop();
        System.out.println("createOrderDetailDTO cost: " + sw.elapsed().getSeconds());
        return orderDetailDTO;
    }

    // 用户信息
    static UserInfo getUserInfo(Long userId) {
        Factory.sleep(3);
        return new UserInfo(String.valueOf(userId), "xinput", 18);
    }

    // 订单信息
    static OrderInfo getOrderInfo(String orderId) {
        Factory.sleep(5);
        return new OrderInfo(orderId);
    }

}
