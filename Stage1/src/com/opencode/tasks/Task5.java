package com.opencode.tasks;

public class Task5 implements Task {
    private String src = "Hello world!";
    @Override
    public void execute() {
        for (int i = 0; i < src.length(); i++) {
            char current = src.charAt(i);
            System.out.println(String.format("Char: %s, Code: %s", current, (int) current));
        }
    }
}
