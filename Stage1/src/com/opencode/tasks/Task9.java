package com.opencode.tasks;

import java.util.Locale;
import java.util.stream.Stream;

import static com.opencode.tasks.TaskUtil.CalcTest;

public class Task9 implements Task {
    private boolean isPalyndrome(String str) {
        for (int leftIndex = 0; leftIndex < str.length(); leftIndex++) {
            int rightIndex = str.length() - leftIndex - 1;
            if (str.charAt(leftIndex) != str.charAt(rightIndex)) {
                return false;
            }
        }
        return true;
    }

    private String normalize(String str) {
        if (str == null) {
            return "";
        }
        return str.toLowerCase(Locale.ROOT).replaceAll("\\s+","");
    }

    private boolean check(String input) {
        return isPalyndrome(normalize(input));
    }

    @Override
    public void execute() {
        System.out.println("Введите слово:");
        String input = TaskUtil.getUserInput();
        if (check(input)) {
            System.out.println("Это палиндром");
        } else {
            System.out.println("Это не палиндром");
        }
    }

    private void runTests() {
        CalcTest<String, Boolean>[] tests = new CalcTest[] {
                // Палиндромы
                new CalcTest("А роза упала на лапу Азора", true),
                new CalcTest("Лёша на полке клопа нашёл", true),
                new CalcTest("Уж редко рукою окурок держу", true),
                new CalcTest("Уж редко рукою окурок держу", true),
                new CalcTest("Лидер бредил", true),
                new CalcTest("Искать такси", true),

                // Не палиндромы
                new CalcTest("ываыа", false),
                new CalcTest("не палиндром", false),
                new CalcTest("не               палиндром", false),
                new CalcTest("(точно ) !не               палиндром", false),
        };

        Stream.of(tests).forEach((test) -> {
            if (check(test.getExpr()) != test.getValue().booleanValue()) {
                throw new RuntimeException("Incorrect!");
            } else{
                System.out.println("correct!");
            }
        });
    }
}
