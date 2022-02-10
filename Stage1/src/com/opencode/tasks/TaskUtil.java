package com.opencode.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskUtil {
    public static String getUserInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException e) {
            System.out.println("При попытке чтения выпало исключение! " + e.getMessage());
            return null;
        }
    }
}
