package com.opencode.tasks;

public class Task2 implements Task {
    @Override
    public void execute() {
        int a = 10, b = 2;
        System.out.println("Integer ops");
        System.out.println(String.format("%s + %s = %s", a, b, a + b));
        System.out.println(String.format("%s - %s = %s", a, b, a - b));
        System.out.println(String.format("%s / %s = %s", a, b, a / b));
        System.out.println(String.format("%s * %s = %s", a, b, a * b));

        float c = 13, d = 4;
        System.out.println("Float ops");
        System.out.println(String.format("%s + %s = %s", c, d, c + d));
        System.out.println(String.format("%s - %s = %s", c, d, c - d));
        System.out.println(String.format("%s / %s = %s", c, d, c / d));
        System.out.println(String.format("%s * %s = %s", c, d, c * d));
    }
}
