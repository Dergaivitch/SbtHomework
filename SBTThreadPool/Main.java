package ru.sbt;
public class Main {
    public static void main(String[] args) {
        ThreadPool pool = new ScalableThreadPool(2,3);

        pool.execute(()-> System.out.println(1));
        pool.execute(()-> System.out.println(2));
        pool.execute(()-> System.out.println(3));
        pool.execute(()-> System.out.println(4));
        pool.execute(()-> System.out.println(5));
        pool.execute(()-> System.out.println(6));
        pool.execute(()-> System.out.println(7));
        pool.execute(()-> System.out.println(8));
        pool.execute(()-> System.out.println(9));

        pool.start();
    }
}