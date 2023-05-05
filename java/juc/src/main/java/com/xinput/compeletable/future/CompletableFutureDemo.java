package com.xinput.compeletable.future;

import com.xinput.entity.MedalInfo;
import com.xinput.entity.UserInfo;
import com.xinput.compeletable.future.service.MedalService;
import com.xinput.compeletable.future.service.UserInfoService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * {@link CompletableFuture} 默认线程池示例
 */
public class CompletableFutureDemo {
  public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException {
    UserInfoService userInfoService = new UserInfoService();
    MedalService medalService = new MedalService();
    long id = 1L;
    long startTime = System.currentTimeMillis();

    // 调用用户服务获取用户基本信息
    CompletableFuture<UserInfo> completableUserInfoFuture = CompletableFuture.supplyAsync(() -> userInfoService.getUserInfo(id));

    Thread.sleep(500); //模拟主线程其它操作耗时

    CompletableFuture<MedalInfo> completableMedalInfoFuture = CompletableFuture.supplyAsync(() -> medalService.getMedalInfo(id));

    UserInfo userInfo = completableUserInfoFuture.get(2, TimeUnit.SECONDS);//获取个人信息结果
    MedalInfo medalInfo = completableMedalInfoFuture.get();//获取勋章信息结果

    System.out.println(userInfo.toString());
    System.out.println(medalInfo.toString());
    System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
  }
}
