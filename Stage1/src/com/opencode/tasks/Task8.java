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

    private boolean isOpenBracket(Character ch) {
        return ch.equals('(');
    }

    private boolean isCloseBracket(Character ch) {
        return ch.equals(')');
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
            if (isOpenBracket(ch)) {
                int j = i + 1;
                for (; j < expression.length(); j++) {
                    char chj = expression.charAt(j);
                    if (isCloseBracket(chj)) {
                        break;
                    }
                }
                String subExpr = expression.substring(i + 1, j);
                System.out.println("got sub string: " + subExpr);
                stack.add(processExpression(subExpr));
                i = j;
                continue;
            }

            if (isNumPart(ch)) {
                currentNumber += ch;
            } else {
                double parsed = Double.parseDouble(currentNumber);

                if (nextNumberNegative) {
                    parsed = -parsed;
                    nextNumberNegative = false;
                }

                if (plannedMul) {
                    double b = stack.removeLast();
                    parsed *= b;
                    plannedMul = false;
                }

                if (plannedDiv) {
                    double b = stack.removeLast();
                    parsed /= b;
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
            double parsed = Double.parseDouble(currentNumber);

            if (nextNumberNegative) {
                parsed = -parsed;
            }

            if (plannedMul) {
                double b = stack.removeLast();
                parsed *= b;
            }

            if (plannedDiv) {
                double b = stack.removeLast();
                parsed /= b;
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
