package tasks;

public class Task2 implements Runnable {
    @Override
    public void run() {
        String input = TaskUtil.getUserInput("Введите символы");
//        System.out.println("Ввод: " + input.replaceAll("[^A-zА-я\\s]", "")); старый вариант решения

        // В качестве алфавита будут русские буквы
        // В качестве других символов пусть будут цифры
        System.out.println("Ввод " + input.replaceAll("\\d", ""));
    }
}
