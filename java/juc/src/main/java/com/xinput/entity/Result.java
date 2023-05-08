package com.xinput.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private int requestId;
    private String name;

    public Result() {
    }

    public Result(int requestId) {
        this.requestId = requestId;
    }
}
