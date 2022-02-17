package tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

public class TaskUtil {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private final static Scanner scanner = new Scanner(System.in);

    public static void notifyError(Exception e) {
        System.out.println("При попытке чтения выпало исключение! " + e.getMessage());
    }

    public static String getUserInput(String message) {
        System.out.println(message);
        try {
            return br.readLine();
        } catch (IOException e) {
            notifyError(e);
            return null;
        }
    }

    public static String getUserInput() {
        return getUserInput("Введите строку");
    }

    public static int askForInteger(String message) {
        System.out.println(message);
        try {
            return scanner.nextInt();
        } catch(InputMismatchException e){
            throw new RuntimeException("Ввод должен не содержать букв и быть целым числом!");
        }
    }

    public static int askForInteger() {
        return askForInteger("Введите число");
    }

    public static char askForSymbol(String message) {
        try {
            return getUserInput(message).charAt(0);
        } catch (Exception e) {
            notifyError(e);
            throw new RuntimeException("Не получилось!");
        }
    }

    public static char askForSymbol() {
        return askForSymbol("Введите символ!");
    }

    public static class Assertions {
        public static void assertTrue(boolean expression, String message) {
            if (!expression) {
                throw new RuntimeException(message);
            }
        }

        public static void assertTrue(boolean expression) {
            assertTrue(expression, "Assertion failed!");
        }
    }

    public static class Test<T, E> {
        T expr;
        E value;
        Comparator<E> comparator;
        public Test(T expr, E value, Comparator<E> comparator) {
            this.value = value;
            this.expr = expr;
            this.comparator = comparator;
        }

        public void doTest(Function<T, E> cb, String message) {
            final E expected = getValue();
            final E actual = cb.apply(getExpr());
            if (comparator.compare(expected, actual) != 0) {
                throw new RuntimeException("expected:" + expected + " actual: " + actual);
            } else {
                System.out.println(message);
            }
        }

        public void doTest(Function<T, E> cb) {
            doTest(cb, "Ok!");
        }

        public E getValue() {
            return value;
        }

        public T getExpr() {
            return expr;
        }
    }
}
