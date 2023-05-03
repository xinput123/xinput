package com.xx.xinput.java.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 枚举 基本特性
 * - 类结构(强类型)
 * - 继承 java.lang.Enum
 * - 不可显示地继承和被继承
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @since
 */
public class EnumClassDemo {
    public static void main(String[] args) {
        // Q1: ONE 到底是第几个定义的
        // Q2: 能否输出所有的成员
        println(Counting.ONE);
        println(Counting.THREE);

        // Q3: 为什么枚举会输出成员名称
        println(CountingEnum.ONE);
        println(CountingEnum.THREE);

        printEnumMeta(CountingEnum.ONE);
        printEnumMeta(CountingEnum.FIVE);

        printCountingMembers();
    }

    public static void println(Counting counting) {
        System.out.println(counting.toString());
    }

    public static void println(CountingEnum countingEnum) {
        System.out.println(countingEnum.toString());
    }

    public static void printEnumMeta(Enum enumeration) {
        // 说明任何枚举都扩展了 java.lang.Enum
        System.out.println("Enumeration type : " + enumeration.getClass());
        // Enum#name = 成员名称
        // Enum#ordinal = 成员定义位置
        System.out.println("member : " + enumeration.name());
        System.out.println("ordinal : " + enumeration.ordinal());
        // values() 方法时 Java 编译器给枚举提升的方法
    }

    public static void printCountingMembers() {
        System.out.println("============= printCountingMembers  =================");
        Stream.of(Counting.values()).forEach(System.out::println);
    }
}

/**
 * 通过 javap com/xx/rookie/java/lang/CountingEnum 可以看到 values() 这个方法，是 Java 编译器做的字节码提升
 * 通过 javap -v com/xx/rookie/java/lang/CountingEnum >> CountingEnum.txt 将详情打印到文件中
 */
enum CountingEnum {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private int value;

    CountingEnum(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Counting : " + value;
    }
}

/**
 * 枚举实际就是个 final 修饰的 class
 * 枚举类：计数
 * 强类型约束(相对于常量)
 */
final class Counting {
    public static final Counting ONE = new Counting(1);
    public static final Counting TWO = new Counting(2);
    public static final Counting THREE = new Counting(3);
    public static final Counting FOUR = new Counting(4);
    public static final Counting FIVE = new Counting(5);

    private int value;

    private Counting(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Counting : " + value;
    }

    /**
     * 不使用字节码提升的方式，而是自己实现 Enum#values() 方法
     */
    public static Counting[] values() {
        Field[] fields = Counting.class.getDeclaredFields();

        // Fileds -> filter -> public static final fields -> get

        return Stream.of(fields).filter(field -> {
            int modifiers = field.getModifiers();
            return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
        }).map(field -> {
            // Field -> Counting
            try {
                return (Counting) field.get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList()).toArray(new Counting[0]);
    }

}