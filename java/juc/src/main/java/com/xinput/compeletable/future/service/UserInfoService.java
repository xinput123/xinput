package com.xinput.compeletable.future.service;

import com.xinput.entity.UserInfo;

public class UserInfoService {
  public UserInfo getUserInfo(Long userId) {
    try {
      Thread.sleep(1000);//模拟调用耗时
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new UserInfo(String.valueOf(userId), "xinput", 18); //一般是查数据库，或者远程调用返回的
  }
}
