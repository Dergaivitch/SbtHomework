package ru.sbt;

/**
 * Created by Dergai on 06.09.2016.
 */
public interface Context {
        int getCompletedTaskCount();

        int getFailedTaskCount();

        int getInterruptedTaskCount();

        void interrupt();

        boolean isFinished();
}
