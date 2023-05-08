package com.xinput.entity;

import lombok.Getter;

@Getter
public class Taskinfo {
    private int id;
    private int cost;

    public Taskinfo(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Taskinfo{" +
                "id=" + id +
                ", cost=" + cost +
                '}';
    }
}
