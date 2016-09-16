package ru.sbt.test.refactoring;


public class Position {
    private final int X;
    private final int Y;

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Position(int x, int y) {
        X = x;
        Y = y;
    }
}
