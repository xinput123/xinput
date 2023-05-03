package com.xx.xinput.java.lang;

/**
 * Cloneable 是一个标记接口，用于表示该类可以克隆
 */
public class CloneableDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        String desc = "Hello";
        Data data = new Data();
        data.setValue(1);
        data.setDesc(desc);

        Data copy = data.clone();

        System.out.println("data == copy " + (data == copy));
        // 输出1
        // 浅 Copy
        // 深浅主要在于对象类型
        System.out.println(copy.getValue());
        // 浅拷贝的话
        // desc -> data.desc() -> clone -> copy.desc
        System.out.println(copy.getDesc() == desc);
        // 深拷贝的话
        System.out.println(copy.getDesc().equals(desc));
    }
}

/**
 * 如果不实现 Cloneable 接口，则该类不能使用 .clone() 方法，否则会提示 CloneNotSupportedException 异常
 */
class Data extends Object implements Cloneable {

    private int value;

    private String desc;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 通常把 protected 访问性提升为 public
     * 强制调整为目标类型
     *
     * @return
     * @throws CloneNotSupportedException
     */
    public Data clone() throws CloneNotSupportedException {
        Data copy = (Data) super.clone();
        // 原生类型没有浅深关系
        // 对象类型需要复制
        copy.desc = new String(this.desc);
//        copy.desc = this.desc;
        return copy;
    }
}