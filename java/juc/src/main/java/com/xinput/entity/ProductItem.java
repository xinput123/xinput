package com.xinput.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品
 */
@Getter
@Setter
public class ProductItem {
    private Long id;
    private String name;
    private Double price;
    private String desc;
    private int sleep;

    public ProductItem(Long id, String name, Double price, String desc, int sleep) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.sleep = sleep;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", desc='" + desc + '\'' +
                ", sleep=" + sleep +
                '}';
    }
}
