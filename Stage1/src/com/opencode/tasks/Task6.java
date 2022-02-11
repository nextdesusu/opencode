package com.opencode.tasks;

import java.util.*;

public class Task6 implements Task {
    //  “ Hello World! ” так в задаче
    private String src = " Hello World! ";

    @Override
    public void execute() {
        SortedMap<Character, Integer> map = new TreeMap<>();
        src.chars()
                .mapToObj((ch) -> (char) ch)
                .forEach((item) -> {
                    map.put(item, 1 + map.getOrDefault(item, 0));
                });

        map.entrySet()
                .stream()
                .filter((entry) -> entry.getValue() > 1)
                .forEach((entry) -> {
                    System.out.println(String.format("%s - %d", entry.getKey(), entry.getValue()));
                });
    }
}
