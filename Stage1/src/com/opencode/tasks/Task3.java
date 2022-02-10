package com.opencode.tasks;

public class Task3 implements Task {
    @Override
    public void execute() {
        boolean t = true, f = false;
        System.out.println(String.format("%s && %s = %s", t, f, t && f));
        System.out.println(String.format("%s || %s = %s", t, f, t || f));
        System.out.println(String.format("%s == %s = %s", t, f, t == f));
        System.out.println(String.format("%s != %s = %s", t, f, t != f));
        System.out.println(String.format("!%s = %s", t, !t));
        System.out.println(String.format("!%s = %s", f, !f));

    }
}
