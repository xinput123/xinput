package com.xinput.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {
    private int requestId;
    private String content;

    public Request() {
    }

    public Request(int requestId, String content) {
        this.requestId = requestId;
        this.content = content;
    }
}
