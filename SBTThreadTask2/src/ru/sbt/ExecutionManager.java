package ru.sbt;

/**
 * Created by Dergai on 06.09.2016.
 */
public interface ExecutionManager {

    Context execute(Runnable callback, Runnable... tasks);

}
