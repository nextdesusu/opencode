package com.opencode.tasks;

import java.util.*;

public class Task6 implements Task {
    //  “ Hello World! ” так в задаче
    private String src = " Hello World! ";

    @Override
    public void execute() {
        Map<Character, Integer> map = new LinkedHashMap<>();
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
