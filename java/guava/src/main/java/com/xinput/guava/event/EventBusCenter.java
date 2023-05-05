package com.xinput.guava.event;

import com.google.common.eventbus.EventBus;

/**
 * Google Guava EventBus 消息发布-订阅异步调用使用
 * <p>
 * EventBus 是Google.Guava提供的消息发布-订阅类库，它实现了观察者设计模式，
 * 消息通知负责人通过EventBus去注册/注销观察者，最后由消息通知负责人给观察者发布消息。
 */
public class EventBusCenter {

  private static EventBus eventBus = new EventBus();

  public EventBusCenter() {
  }

  public static EventBus getInstance() {
    return eventBus;
  }

  /**
   * 注册订阅者以订阅消息
   */
  public static void register(Object obj) {
    eventBus.register(obj);
  }

  /**
   * 注销所有已注册的订户方法
   */
  public static void unregister(Object obj) {
    eventBus.unregister(obj);
  }

  /**
   * 将事件发布到所有注册的订户.
   * s事件发布到所有订阅者后，无论订阅者抛出任何异常，此方法都将成功返回
   */
  public static void post(Object obj) {
    eventBus.post(obj);
  }
}
