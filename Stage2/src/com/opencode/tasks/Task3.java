package tasks;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Task3 implements Runnable {
    @Override
    public void run() {
        String words = TaskUtil.getUserInput("Введите последовательность слов.");

        int k = TaskUtil.askForInteger("Введите k позицию символа в слове для замены");
        char symbol = TaskUtil.askForSymbol("Введите новый символ (если вы введете больше чем один символ возьмется первый!)");
        System.out.println("k: " + k + " символ: " + symbol);

        System.out.println("Результат: " + computeResult(words, k, symbol));
//        runTests();
    }

    private String computeResult(String words, int k, char symbol) {
        return Arrays
                .stream(words.split("\\s"))
                .filter((item) -> !item.equals(" "))
                .map((item) -> {
                    if (k > item.length() - 1) {
                        return item;
                    }

                    return item.substring(0, k) + symbol + item.substring(k + 1);
                })
                .collect(Collectors.joining(" "));
    }

    private void runTests() {
        String result1 = computeResult("r rrr", 1, '!');
        TaskUtil.Assertions.assertTrue(result1.equals("r r!r"), result1);

        String result2 = computeResult("r rrr", 0, '!');
        TaskUtil.Assertions.assertTrue(result2.equals("! !rr"), result2);

        String result3 = computeResult("r rrr", 3, '!');
        TaskUtil.Assertions.assertTrue(result3.equals("r rrr"), result3);

        String result4 = computeResult("ШЛА САША ПО ШОССЕ И СОСАЛА СУШКУ!", 1, 'e');
        TaskUtil.Assertions.assertTrue(result4.equals("ШeА СeША Пe ШeССЕ И СeСАЛА СeШКУ!"), result4);

        String result5 = computeResult("ШЛА САША ПО ШОССЕ И СОСАЛА СУШКУ!", 6, 'e');
        TaskUtil.Assertions.assertTrue(result5.equals("ШЛА САША ПО ШОССЕ И СОСАЛА СУШКУ!"), result5);
    }
}
