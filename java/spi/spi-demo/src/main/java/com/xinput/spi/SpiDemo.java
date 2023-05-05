package com.xinput.spi;

import com.xinput.spi.api.Say;

import java.util.ServiceLoader;

public class SpiDemo {
  public static void main(String[] args) {
    // ServiceLoader是java.util提供的用于加载固定类路径下文件的一个加载器，
    // 正是它加载了对应接口声明的实现类。
    ServiceLoader<Say> printerLoader = ServiceLoader.load(Say.class);
    for (
        Say say : printerLoader) {
      say.say();
    }
  }
}
