package ru.sbt;

public class FoldException extends Throwable {
    public FoldException() {
        System.out.println("Amount should be folded 100, enter another amount");
    }

    public FoldException(String message) {
        super(message);
    }

    public FoldException(String message, Throwable cause) {
        super(message, cause);
    }
}
