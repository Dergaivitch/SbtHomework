package ru.sbt.test.refactoring;

/**
 * Created by Dergai on 15.09.2016.
 */
public class Field {
    private final int X;
    private final int Y;

    public Field(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
