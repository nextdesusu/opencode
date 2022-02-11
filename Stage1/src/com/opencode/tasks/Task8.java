package com.opencode.tasks;

import java.util.*;
import java.util.stream.Stream;

import static com.opencode.tasks.TaskUtil.CalcTest;

public class Task8 implements Task{
    @Override
    public void execute() {
        System.out.println("Введите математическое выражение!");
        String input = TaskUtil.getUserInput();
        System.out.println(processExpression(input));
    }

    private String normalize(String input) {
        if (input == null) {
            return "";
        }
        return input.replaceAll("\\s+","");
    }

    private Double processExpression(String expression) {
        ParsingProcess parsingProcess = new ParsingProcess();
        char[] chars = normalize(expression).toCharArray();
        for (char ch : chars) {
            parsingProcess.processChar(ch);
        }
        return parsingProcess.compute();
    }

    private class ParsingProcess {
        private boolean nextNumberNegative = false;
        private boolean plannedMul = false;
        private boolean plannedDiv = false;
        private String currentNumber = "";
        private LinkedList<Double> stack = new LinkedList<>();
        private Set<Character> ops = new HashSet<>(List.of('+', '-', '/', '*'));

        private boolean isOp(Character ch) {
            return ops.contains(ch);
        }

        /*
        Этот метод проверяет является ли символ частью числа
        в состав числа входят .0123456789
         */
        private boolean isNumPart(Character ch) {
            // точка это тоже часть числа.
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

        private void addNext() {
            if (currentNumber == "") {
                return;
            }
            double parsed = Double.parseDouble(currentNumber);

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

        public Double compute() {
            addNext();
            return stack
                    .stream()
                    .reduce(0.0, Double::sum);
        }


        public void processChar(Character ch) {
            if (isNumPart(ch)) {
                currentNumber += ch;
            } else {
                addNext();
                if(isOp(ch)) {
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
                    }
                }
            }
        }
    }

    private void runTests() {
        CalcTest<String, Double>[] tests = new CalcTest[] {
                new CalcTest<>("213213+234234/3", 291291.0),
                new CalcTest<>("-3", -3.0),
                new CalcTest<>("1+1+1+1+1+1+1+1+1+1", 10.0),
                new CalcTest<>("4/4", 1.0),
                new CalcTest<>("4/4", 1.0),
                new CalcTest<>("23+0.3*23/45+56/4-545*23", -12497.8466667),
                new CalcTest<>("1/1/1/1/11/1/1/1", 0.0909090909),
                new CalcTest<>("5*232*2323*342", 921580560.0),
                new CalcTest<>("5*-2+4+4-2*4-56*-1", 46.0)
        };

        Stream.of(tests).forEach((test) -> {
            System.out.println("Testing: " + test.getExpr());
            Double guess = processExpression(normalize(test.getExpr()));
            // Сравнение с учетом погрешности
            if (Math.abs(guess - test.getValue()) > 0.0001) {
                throw new RuntimeException(guess + " !== " + test.getValue());
            } else {
                System.out.println(test.getValue() + " : " + guess);
                System.out.println(test.getExpr() + " ok!");
            }
        });
    }
}
