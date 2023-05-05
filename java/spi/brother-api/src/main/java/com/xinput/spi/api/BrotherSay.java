package com.xinput.spi.api;

/**
 * @Author: xinput
 * @Date: 2019-03-20 01:20
 */
public class BrotherSay implements Say {
    @Override
    public void say() {
        System.out.println("Brother say!");
    }
}
