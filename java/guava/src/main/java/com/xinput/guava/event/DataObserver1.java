package com.xinput.guava.event;

import com.google.common.eventbus.Subscribe;

/**
 * 观察者1
 */
public class DataObserver1 {

  /**
   * 只有通过@Subscribe注解的方法才会被注册进EventBuss,而且方法有且只能有1个参数
   *
   * @param msg
   */
  @Subscribe
  public void func(String msg) {
    System.out.println("String msg: " + msg);
  }

  @Subscribe
  public void num(Integer num) {
    System.out.println("num msg: " + num);
  }
}
