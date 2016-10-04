package ru.sbt;

public class TooMuchTryException extends Throwable {
    public TooMuchTryException() throws InterruptedException {

    }

    public TooMuchTryException(String message) {
        super(message);
    }

    public TooMuchTryException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooMuchTryException(Throwable cause) {
        super(cause);
    }

    public TooMuchTryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

