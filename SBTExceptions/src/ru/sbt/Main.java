package ru.sbt;

/**
 * Created by Dergai on 16.09.2016.
 */
public class Main {
    public static void main(String[] args) throws NoPermissionException, InterruptedException, TooMuchTryException, UnreachableException {
        User alex = new User(100,1234);
        Terminal terminal = new TerminalImpl(new TerminalServer(), new PinValidator());
        alex = terminal.deposit(alex,40);
        alex = terminal.deposit(alex,400);

        terminal.checkAmount(alex);
    }
}
