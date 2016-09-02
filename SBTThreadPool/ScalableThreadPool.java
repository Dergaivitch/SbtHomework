package ru.sbt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ScalableThreadPool implements ThreadPool {
    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private final int minThreadCount;
    private final int maxThreadCount;

    public ScalableThreadPool(int minThreadCount, int  maxThreadCount) {
        this.minThreadCount = minThreadCount;
        this.maxThreadCount = maxThreadCount;
    }

    @Override
    public void start() {
        for (int i = 0; i < minThreadCount; i++) {
            new Worker().start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (tasks) {
            tasks.add(runnable);
            tasks.notifyAll();
            //если tasks не пуст создать нового воркера
        }
    }

    public class Worker extends Thread {
        @Override
        public void run() {

            Runnable r;

            while (true) {

                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                            //если потоков больше то убить себя
                        } catch (InterruptedException ignored) {
                        }
                    }

                    r = tasks.poll();
                }

                try {
                    r.run();
                } catch (RuntimeException e) {
                }
            }
        }


    }
}