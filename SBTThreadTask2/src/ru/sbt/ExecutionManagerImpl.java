package ru.sbt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Dergai on 06.09.2016.
 */
public class ExecutionManagerImpl implements ExecutionManager {

    ExecutorService pool = Executors.newFixedThreadPool(3);
    Future[] returning;


    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        int i;
        returning = new Future[tasks.length + 1];
        for (i = 0; i < tasks.length; i++) {
             returning[i] = pool.submit(tasks[i]);
        }
        returning[i + 1] = pool.submit(callback);
        return new ContextImpl(returning);
    }
}
