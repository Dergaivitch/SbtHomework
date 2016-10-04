package ru.sbt;

public class TerminalServer {

    public User withdraw(User user, int amount) throws NoMoneyException {
        synchronized (user) {
            if (user.getAccount() - amount > 0) {
                return new User(user.getAccount() - amount, user.getPassword());
            }
            else throw new NoMoneyException();
        }
    }

    public User deposit(User user, int amount) {
        synchronized (user) {
            return new User(user.getAccount() + amount, user.getPassword());
        }

    }

    public int checkAmount(User user) {
        return user.getAccount();
    }

    public boolean checkPassword(int password, User user) {
        return password == user.getPassword();
    }
}
