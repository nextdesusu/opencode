package com.opencode.tasks;

public class Task4 implements Task {
    private int ARRAY_SIZE = 30;
    private int min = 10;
    private int max = 30;

    private int generateNumber() {
        return (int) Math.floor(Math.random()*(max-min+1)+min);
    }

    @Override
    public void execute() {
        int[] array = new int[ARRAY_SIZE];
        for (int i = 0; i < array.length; i ++) {
            array[i] = generateNumber();
        }
        String numString = "";
        for (int i = 0; i < array.length; i++) {
            numString += " " + array[i];
            if ((i+1) % 10 == 0) {
                System.out.println(numString);
                numString = "";
            }
        }
    }
}
