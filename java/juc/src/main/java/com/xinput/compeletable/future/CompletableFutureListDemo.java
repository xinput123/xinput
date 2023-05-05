package com.xinput.compeletable.future;

import com.xinput.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 循环处理一个 List 中的数据
 * 使用CompletableFuture异步处理list里的每个user的address,并获取处理后的结果集
 */
public class CompletableFutureListDemo {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "张三", "南山区"));
        list.add(new User(2, "李四", "宝安区"));
        list.add(new User(3, "王五", "福田区"));
        list.add(new User(4, "赵六", "罗湖区"));

        // 1.runAsync-无返回值
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("线程名称=" + Thread.currentThread().getName());
            System.out.println("=======runAsync开始执行========");
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.forEach(s -> {
                s.setAddress("SZ-" + s.getAddress());
            });
            System.out.println("=======runAsync执行end========");
        });
        System.out.println("主线程名称=" + Thread.currentThread().getName());
        completableFuture.join();
        System.out.println("list=" + list);
    }
}
