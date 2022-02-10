package com.opencode.tasks;

import java.util.*;

public class Task8 implements Task{
    private Set<Character> ops = new HashSet<>(List.of('+', '-', '/', '*'));

    private boolean isOp(Character ch) {
        return ops.contains(ch);
    }

    private boolean isNumPart(Character ch) {
        if (ch.equals('.')) {
            return true;
        }
        try {
            Integer.parseInt(ch.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String normalize(String input) {
        if (input == null) {
            return "";
        }
        return input.replaceAll("\\s+","");
    }

    private Double processExpression(String expression) {
        LinkedList<Double> stack = new LinkedList<>();

        boolean nextNumberNegative = false;
        boolean plannedMul = false;
        boolean plannedDiv = false;
        String currentNumber = "";
        for (int i = 0; i < expression.length(); i++) {
            char ch  = expression.charAt(i);

            if (isNumPart(ch)) {
                currentNumber += ch;
            } else {
                double parsed = 0.0;
                try {
                    parsed = Double.parseDouble(currentNumber);
                } catch (Exception e) {
                    continue;
                }

                if (nextNumberNegative) {
                    parsed = -parsed;
                    nextNumberNegative = false;
                }

                if (plannedMul) {
                    double b = stack.removeLast();
                    parsed = b * parsed;
                    plannedMul = false;
                }

                if (plannedDiv) {
                    double b = stack.removeLast();
                    parsed = b / parsed;
                    plannedDiv = false;
                }
                stack.add(parsed);
                currentNumber = "";
            }

            if (isOp(ch)) {
                switch (ch) {
                    case '-':
                        nextNumberNegative = !nextNumberNegative;
                        break;
                    case '*':
                        plannedMul = true;
                        break;
                    case '/':
                        plannedDiv = true;
                        break;
                    default:
                        break;
                }
            }
        }
        if (currentNumber != "") {
            double parsed = 0.0;
            try {
                parsed = Double.parseDouble(currentNumber);
            } catch (Exception e) {
                System.out.println("Shouldnt be there!");
            }


            if (nextNumberNegative) {
                parsed = -parsed;
            }

            if (plannedMul) {
                double b = stack.removeLast();
                parsed = b * parsed;
            }

            if (plannedDiv) {
                double b = stack.removeLast();
                parsed = b / parsed;
            }
            stack.add(parsed);
        }
        return stack
                .stream()
                .reduce(0.0, (a, b) -> a + b);
    }

    @Override
    public void execute() {
        System.out.println("Введите математическое выражение!");
        String input = TaskUtil.getUserInput();
        System.out.println(processExpression(normalize(input)));
    }
}
