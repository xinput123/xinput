package com.xinput.entity;

import com.xinput.util.Factory;

import java.util.concurrent.ThreadLocalRandom;

public class Shop {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shop(String name) {
        this.name = name;
    }

    /**
     * 根据产品名查找价格
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    /**
     * 计算价格
     */
    private double calculatePrice(String product) {
        int delay = Factory.getRandom(3);
        System.out.println("thread: " + Thread.currentThread().getName() + " 计算耗时: " + delay);
        delay(delay);
        System.out.println("thread: " + Thread.currentThread().getName() + " 计算完成");
        //random.nextDouble()随机返回折扣
        return ThreadLocalRandom.current().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 通过睡眠模拟其他耗时操作
     */
    private void delay(int delay) {
        Factory.sleep(delay);
    }
}
