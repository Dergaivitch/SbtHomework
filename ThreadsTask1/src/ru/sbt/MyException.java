package ru.sbt;

/**
 * Created by Dergai on 06.09.2016.
 */
public class MyException extends RuntimeException {
    MyException(Exception e) {
        System.out.println(e);
    }
}
