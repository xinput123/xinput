package com.xinput.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MedalInfo {

  private String id;

  private String name;

  public MedalInfo(String id, String name) {
    this.id = id;
    this.name = name;
  }
}
