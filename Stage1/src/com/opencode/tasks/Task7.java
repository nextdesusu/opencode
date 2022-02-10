package com.opencode.tasks;

public class Task7 implements Task{
    @Override
    public void execute() {
        int times = 100;
        for (int i = times; i > 0; i--) {
            System.out.println(String.format("%s бутылок стояло на столе, одна из них упала и разбилась.", i));
        }
    }
}
