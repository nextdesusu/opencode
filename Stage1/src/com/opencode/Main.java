package com.opencode;

import com.opencode.tasks.*;

public class Main {
    private static Task[] tasks = new Task[] {
            new Task1(), new Task2(), new Task3(), new Task4(), new Task5(), new Task6(), new Task7(), new Task8(), new Task9(),
    };

    private static void executeTask(Task task) {
        System.out.println("Демонстрация задачи: " + task.getClass().getSimpleName());
        task.execute();
        System.out.println("********************************************\n\n\n");
    }

    private static void executeAll() {
        for (Task task : tasks) {
            executeTask(task);
        }
    }

    private static void executeNth(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.length) {
            throw new RuntimeException("Не существует задачи с таким номером");
        }
        executeTask(tasks[taskNumber - 1]);
    }

    public static void main(String[] args) {
        // Выполнить таск с конкретным номером
        // индексация тасков начинается с 1
         executeNth(8);

        // Выполнить все таски
//        executeAll();
    }
}
