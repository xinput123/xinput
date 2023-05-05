package com.xinput.web;

import com.xinput.web.util.WsUtils;

public class WebClient {
    public static void main(String[] args) {
        String message = "测试";
        try {
            Object[] objects = WsUtils.webService(message);
            System.out.println(String.valueOf(objects[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
