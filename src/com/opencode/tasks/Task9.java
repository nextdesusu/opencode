package com.opencode.tasks;

import java.util.Locale;

public class Task9 implements Task {
    private boolean isPalyndrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i != j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private String normalize(String str) {
        if (str == null) {
            return "";
        }
        return str.toLowerCase(Locale.ROOT);
    }

    @Override
    public void execute() {
        System.out.println("Введите слово:");
        String input = TaskUtil.getUserInput();
        if (isPalyndrome(normalize(input))) {
            System.out.println("Это палиндром");
        } else {
            System.out.println("Это не палиндром");
        }
    }
}
