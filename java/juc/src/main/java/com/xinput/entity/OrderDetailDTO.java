package com.xinput.entity;

public class OrderDetailDTO {
    private UserInfo userInfo;
    private OrderInfo orderInfo;

    public OrderDetailDTO(UserInfo userInfo, OrderInfo orderInfo) {
        this.userInfo = userInfo;
        this.orderInfo = orderInfo;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "userInfo=" + userInfo +
                ", orderInfo=" + orderInfo +
                '}';
    }
}
