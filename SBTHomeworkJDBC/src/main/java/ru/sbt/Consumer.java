package ru.sbt;

/**
 * Created by Dergai on 18.10.2016.
 */
public interface Consumer<T> {
    void accept(T t) throws Exception;
}
