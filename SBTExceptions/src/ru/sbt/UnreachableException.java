package ru.sbt;

 class UnreachableException extends Throwable {
     UnreachableException() {
         System.out.println("Smth wrong");;
    }

    public UnreachableException(String message) {
        super(message);
    }

    public UnreachableException(String message, Throwable cause) {
        super(message, cause);
    }
}
