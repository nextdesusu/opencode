package com.opencode.additional.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskListCli implements Runnable {
    private boolean running = false;

    private TaskList<Integer> list = new TaskList();

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private final static Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        pollUserForCommands();
    }

    private void pollUserForCommands() {
        running = true;
        list = new TaskList<>();
        while(running) {
            printInfo();
            String command = getUserInput().replaceAll("\\s", "");
            processCommand(command);
        }
    }

    private void processCommand(String command) {
        switch (command) {
            case "add":
                processAddition();
                break;
            case "insert":
                processInsertion();
                break;
            case "remove":
                processRemoval();
                break;
            case "sort":
                processSort();
                break;
            case "clear":
                processClear();
                break;
            case "random":
                processRandom();
                break;
            case "quit":
                running = false;
                return;
            default:
                System.out.println("Команда не распознана!");
        }
    }

    private void processAddition() {
        list.add(askForInteger("Введите целое число для добавления"));
    }

    private void processInsertion() {
        try {
            list.insertAt(
                    askForInteger("Введите индекс"),
                    askForInteger("Введите элемент")
            );
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Индекс находится за пределами массива!");
        }
    }

    private void processRandom() {
        int upper = askForInteger("Верхняя граница");
        int lower = askForInteger("Нижняя граница");
        int times = askForInteger("Сколько?");
        for (int i = 0; i < times; i++) {
            list.add(getRandomNumber(lower, upper));
        }
    }

    private void processRemoval() {
        try {
            list.removeAt(
                    askForInteger("Введите индекс")
            );
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Индекс находится за пределами массива!");
        }
    }

    private void processSort() {
        list.sort(TaskList.Ordering::compareIntegers);
    }

    private void processClear() {
        list.clear();
    }

    private void printInfo() {
        System.out.println("Текущее состояние массива;");
        list.print();
        System.out.println("добавить элемент: add \n" +
                "вставить элемент в середину: insert\n" +
                "удалить элемент: remove\n" +
                "отсортировать список: sort\n" +
                "очистить список: clear\n" +
                "добавить случайных чисел: random\n" +
                "либо завершить работу: quit\n");
    }

    private String getUserInput() {
        try {
            return br.readLine();
        } catch (IOException e) {
            System.out.println("При попытке чтения выпало исключение! " + e.getMessage());
            return null;
        }
    }

    private static int askForInteger(String message) {
        System.out.println(message);
        try {
            return scanner.nextInt();
        } catch(InputMismatchException e){
            throw new RuntimeException("Ввод должен не содержать букв и быть целым числом!");
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
