package ru.sbt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dergai on 06.09.2016.
 */
public class ContextImpl implements Context {

    Future[] future;
    AtomicInteger interrupted;


    ContextImpl(Future... future) {
        this.future = future;


    }

    @Override
    public int getCompletedTaskCount() {
        int count = 0;
        for (Future f : future) {
            if(f.isDone()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getFailedTaskCount() {
        int count = 0;
        for (Future f : future) {
            if(f.isCancelled()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getInterruptedTaskCount() {
        return interrupted.get();
    }

    @Override
    public synchronized void interrupt() {
        for (Future f : future) {
            if (f.cancel(true)) {
                interrupted.getAndIncrement();
            }
        }
    }

    @Override
    public boolean isFinished() {
        return future[future.length-1].isDone();
    }
}
