package com.xinput.entity;

public class OrderInfo {
    private String orderId;

    public OrderInfo(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
