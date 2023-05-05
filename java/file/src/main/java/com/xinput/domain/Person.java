package com.xinput.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    @ExcelProperty("ID")
    private long id;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("年龄")
    private int age;
    @ExcelProperty("出生日期")
    private String birthday;
    @ExcelProperty("性别")
    private String sex;
}
