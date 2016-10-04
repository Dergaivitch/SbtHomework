package ru.sbt;


public class NoMoneyException extends Throwable {
    public NoMoneyException() {
        System.out.println("No money");
    }

    public NoMoneyException(String message) {
        super(message);
    }

    public NoMoneyException(String message, Throwable cause) {
        super(message, cause);
    }
}
