package ru.sbt;

public class Main {
    public static void main(String[] args) {


        ExecutionManager a = new ExecutionManagerImpl();
        Runnable[] tasks = new Runnable[5];
        Runnable callback = () -> {
            System.out.println("end!!!");
        };

        for (int j = 0; j < tasks.length; j++) {
            tasks[j] = () -> {
                    for (int i = 0; i < 10; i++)
                        System.out.println(Thread.currentThread().getName() + " // " + i);
                };


        }

        ExecutionManager em = new ExecutionManagerImpl();
        Context c = em.execute(callback,tasks);
        System.out.println(c.getCompletedTaskCount());
    }
}

