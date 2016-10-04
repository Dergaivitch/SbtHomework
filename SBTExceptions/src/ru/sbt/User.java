package ru.sbt;


public class User {

    private final int account;
    private final int password;

    public User(int account, int password) {
        this.account = account;
        this.password = password;
    }

    public int getAccount() {
        return account;
    }

    public int getPassword() {
        return password;
    }
}
