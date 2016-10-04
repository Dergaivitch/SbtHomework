package ru.sbt;

public class PingPong extends Thread {

    private final String act;
    private final boolean ping;
    private static boolean turn;
    private static final Object lock = new Object();

    PingPong(boolean side) {
        this.ping = side;
        act = side ? "Ping" : "Pong";
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (true) {
                while (turn != ping) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {}
                }
                System.err.println(act);
                turn = !turn;
                lock.notify();
            }
        }
    }


    public static void main(String[] args) {
        new PingPong(true).start();
        new PingPong(false).start();
    }
}