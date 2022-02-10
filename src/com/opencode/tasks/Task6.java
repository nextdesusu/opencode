package com.opencode.tasks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Task6 implements Task {
    private String src = "Hello world!";

    @Override
    public void execute() {
        Set<Character> set = new HashSet<>();
        String noRepeats = "";
        for (int i = 0; i < src.length(); i++){
            char current = src.charAt(i);
            if (!set.contains(current)) {
                noRepeats += current;
            }
            set.add(current);
        }
        System.out.println(String.format("String: \"%s\", length: %s", noRepeats, set.size()));
    }
}
