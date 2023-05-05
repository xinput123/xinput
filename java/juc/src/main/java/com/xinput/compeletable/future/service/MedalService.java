package com.xinput.compeletable.future.service;

import com.xinput.entity.MedalInfo;

public class MedalService {

  public MedalInfo getMedalInfo(long mediaId) {
    try {
      Thread.sleep(1000); //模拟调用耗时
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new MedalInfo(String.valueOf(mediaId), "跑步达人");
  }
}
