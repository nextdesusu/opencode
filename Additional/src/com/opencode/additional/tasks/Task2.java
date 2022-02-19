package com.opencode.additional.tasks;

public class Task2 implements Runnable {
    @Override
    public void run() {
        TaskListCli taskListCli = new TaskListCli();
        taskListCli.run();
    }
}
