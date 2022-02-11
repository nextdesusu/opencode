package com.opencode.tasks;

public class Task7 implements Task{
    private String findEnding(int n) {
        int valuesCount = n % 100;
        int num = valuesCount % 10;

        if (valuesCount > 10 && valuesCount < 20) return "бутылок";
        if (num > 1 && num < 5) return "бутылки";
        if (num == 1) return "бутылка";

        return "бутылок";
    }

    @Override
    public void execute() {
        int times = 100;
        for (int i = times; i > 0; i--) {
            System.out.println(String.format("%d %s стояло на столе, одна из них упала и разбилась.", i, findEnding(i)));
        }
    }
}
