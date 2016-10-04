package ru.sbt;

import java.util.concurrent.Callable;

public class Task1<T> {

    private final Callable<? extends T> task;
    private volatile boolean calc;
    private volatile T result;
    private boolean broken;
    private volatile Exception e;


    public Task1(Callable<? extends T> callable) {
        this.task = callable;


    }

    public T get() {

        if (calc) return result;
        synchronized (this) {
            if (calc) return result;
            broken = false;
            try {
                result = task.call();
                calc = true;
            } catch (Exception e) {
                broken = true;
                this.e = e;
            }
        }
        if (broken) throw new MyException(e);
        return result;




    }
}