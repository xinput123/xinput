package com.xinput.compeletable.future;

import com.xinput.compeletable.future.service.MedalService;
import com.xinput.compeletable.future.service.UserInfoService;
import com.xinput.entity.MedalInfo;
import com.xinput.entity.UserInfo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 使用Future来进行异步调用
 * <p>
 * Future对于结果的获取，不是很友好，只能通过阻塞 或者轮询的方式 得到任务的结果。
 * <p>
 * Future.get() 就是阻塞调用，在线程获取结果之前get方法会一直阻塞 。
 * Future提供了一个isDone方法，可以在程序中轮询这个方法查询 执行结果。
 */
public class FutureDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        UserInfoService userInfoService = new UserInfoService();
        MedalService medalService = new MedalService();
        long id = 1L;
        long startTime = System.currentTimeMillis();

        // 调用用户服务获取用户基本信息
        FutureTask<UserInfo> userInfoFutureTask = new FutureTask<>(new Callable<UserInfo>() {
            @Override
            public UserInfo call() throws Exception {
                return userInfoService.getUserInfo(id);
            }
        });
        executorService.submit(userInfoFutureTask);

        Thread.sleep(500); //模拟主线程其它操作耗时

        // 调用勋章服务获取勋章信息
        FutureTask<MedalInfo> medalInfoFutureTask = new FutureTask<>(new Callable<MedalInfo>() {
            @Override
            public MedalInfo call() throws Exception {
                return medalService.getMedalInfo(id);
            }
        });
        executorService.submit(medalInfoFutureTask);

        UserInfo userInfo = userInfoFutureTask.get();//获取个人信息结果
        MedalInfo medalInfo = medalInfoFutureTask.get();//获取勋章信息结果

        System.out.println(userInfo.toString());
        System.out.println(medalInfo.toString());
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
