import tasks.*;

public class Main {
    private static Runnable[] tasks = new Runnable[] {
            new Task1(), new Task2(), new Task3(), new Task4(), new Task5(),
    };

    private static void executeTask(Runnable task) {
        System.out.println("Демонстрация задачи: " + task.getClass().getSimpleName());
        System.out.println("\n\n\n");
        task.run();
        System.out.println("\n\n\n");
        System.out.println("********************************************");
    }

    private static void executeAll() {
        for (Runnable task : tasks) {
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
        executeNth(5);

        // Выполнить все таски
        // executeAll();
    }
}
