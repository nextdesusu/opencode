package com.opencode.additional.tasks;

public class TaskListCli implements Runnable {
    @Override
    public void run() {
        TaskList<Integer> list = new TaskList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
//        Обратная сортировка!
        list.sort((a, b) -> TaskList.Ordering.from(b.intValue(), a.intValue()));
        //        Нормальная сортировка!
        list.sort(TaskList.Ordering::from);
//        list.clear();
//        list.insertAt(100, 1000);
//        list.removeAt(99);
//        System.out.println(list.getLength());
        list.print();
    }
}
