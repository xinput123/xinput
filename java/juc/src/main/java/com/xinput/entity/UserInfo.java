package com.xinput.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfo {

  private String id;

  private String name;

  private int age;

  public UserInfo(String id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }
}
