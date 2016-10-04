package ru.sbt;

import java.util.Scanner;

public class TerminalImpl implements Terminal {

    private final TerminalServer server;
    private final PinValidator pinValidator;
    private boolean access;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    public synchronized void getAccess(User user) throws InterruptedException, TooMuchTryException, UnreachableException {
        access = pinValidator.getAccess(user, server);
    }

    private boolean checkFold(int amount) {
        return amount % 100 == 0;
    }

    public User withdraw(User user, int amount) throws NoPermissionException, TooMuchTryException, InterruptedException, UnreachableException, NoMoneyException {
        int newAmount = -1;

        while (true) {
            if (access) {
                if (!checkFold(amount) && ((newAmount == -1) || (!checkFold(newAmount)))) {
                    try {
                        throw new FoldException();
                    } catch (FoldException e) {
                        newAmount = new Scanner(System.in).nextInt();
                        continue;
                    }
                }
                return newAmount == -1 ? server.withdraw(user, amount) : server.withdraw(user, newAmount);

            } else {
                try {
                    throw new NoPermissionException();
                } catch (NoPermissionException e) {
                    getAccess(user);
                }
            }
        }
    }


    public User deposit(User user, int amount) throws NoPermissionException, TooMuchTryException, InterruptedException, UnreachableException {
        int newAmount = -1;

        while (true) {
            if (access) {
                if (!checkFold(amount) && ((newAmount == -1) || (!checkFold(newAmount)))) {
                    try {
                        throw new FoldException();
                    } catch (FoldException e) {
                        newAmount = new Scanner(System.in).nextInt();
                        continue;
                    }
                }
                return newAmount == -1 ? server.deposit(user, amount) : server.deposit(user, newAmount);
            } else {
                try {
                    throw new NoPermissionException();
                } catch (NoPermissionException e) {
                    getAccess(user);
                }
            }
        }
    }

    public void checkAmount(User user) throws NoPermissionException, TooMuchTryException, InterruptedException, UnreachableException {
        if (access) {
            System.out.println(server.checkAmount(user));
        } else {
            try {
                throw new NoPermissionException();
            } catch (NoPermissionException e) {
                getAccess(user);
                checkAmount(user);
            }
        }

    }
}
