package ru.sbt;

import java.sql.Time;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Dergai on 16.09.2016.
 */
public class PinValidator {
    private int failsCount = 0;
    private final short maxFails = 3;

    public boolean getAccess(User user, TerminalServer server) throws TooMuchTryException, InterruptedException, UnreachableException {
        try {
            System.out.println("Getting access");
            while (failsCount < maxFails) {
                Scanner sc = new Scanner(System.in);
                int password = sc.nextInt();
                if (server.checkPassword(password, user)) return true;

                failsCount++;
                if (failsCount >= 3) throw new TooMuchTryException();
                System.out.print("Error password, attempts: " + (maxFails - failsCount));

            }

        } catch (TooMuchTryException e) {
            long start = System.currentTimeMillis();
            while (start + 5000 > System.currentTimeMillis() ) {
                if (new Scanner(System.in).hasNext())
                    System.out.println("wait " + (start + 5000 -  System.currentTimeMillis()) + "ms");
            }
            failsCount = 0;
            getAccess(user, server);
        }
        throw new UnreachableException();

    }
}
