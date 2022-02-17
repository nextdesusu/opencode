package tasks;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Task4 implements Runnable {
    @Override
    public void run() {
        String rawWords = TaskUtil.getUserInput("Введите слова");
//        прочитать текст пример слов много разных меньшее э 1 2 большее большееслово большееслово большееслово 123456789012

        List<String> words = Arrays
                .stream(rawWords.split("\\s"))
                .filter((item) -> !" ".equals(item))
                .collect(Collectors.toList());

        long maxLength = words
                .stream()
                .mapToLong((word) -> word.length())
                .max()
                .getAsLong();

        long minLength = words
                .stream()
                .mapToLong((word) -> word.length())
                .min()
                .getAsLong();

        System.out.println("Слова максимальной длины: " + words
                .stream()
                .filter((word) -> word.length() == maxLength)
                .collect(Collectors.joining(", "))
        );

        System.out.println("Слова минимальной длины: " + words
                .stream()
                .filter((word) -> word.length() == minLength)
                .collect(Collectors.joining(", "))
        );
    }
}
