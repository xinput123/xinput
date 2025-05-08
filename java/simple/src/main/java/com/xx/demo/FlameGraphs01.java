package com.xx.demo;

public class FlameGraphs01 {

    private static final int MAX = 9999999;

    public static void main(String[] args) {
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=============");
        for (int i = 0; i < MAX; i++) {
            if (i < MAX / 3) {
                testA();
            } else if (i < MAX / 3 * 2) {
                testB();
            } else {
                testC();
            }
        }
    }

    private static int testC() {
        int tmp = 0;
        for (int i = 0; i < 20000; i++) {
            tmp += i;
        }
        return tmp;
    }

    private static int testB() {
        int tmp = 0;
        for (int i = 0; i < 30000; i++) {
            tmp += i;
        }
        return tmp;
    }

    private static int testA() {
        int tmp = 0;
        for (int i = 0; i < 50000; i++) {
            tmp += i;
        }
        return tmp;
    }
}
