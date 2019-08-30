/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static final int THREADS_NUMBER = 10000;
    private static int counter;


    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread tread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " " + Thread.currentThread().getState());
            }
        };
        tread0.start();
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
        System.out.println(tread0.getName() + " " + tread0.getState());

        final MainConcurrency mc = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread trhred = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mc.inc();
                }
            }
            );
            trhred.start();
            threads.add(trhred);
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Thread.sleep(500);
        System.out.println(counter);
        DeadLock dl = new DeadLock();
        dl.tread1.start();
        dl.tread2.start();
        Thread.sleep(500);
        System.out.println(dl.tread1.getState());
        System.out.println(dl.tread2.getState());
    }

    private synchronized void inc() {
        counter++;
    }

    private static class DeadLock {
        private final static Object object1 = new Object();
        private final static Object object2 = new Object();

        Thread tread1 = new Thread() {
            @Override
            public void run() {
                synchronized (object1) {
                    System.out.println(getName() + " synchronized w object1");
                    System.out.println(getName() + " do somethings");
                    for (long i = 0; i < Long.MAX_VALUE; i++) {
                        int a = Integer.MAX_VALUE;
                    }
                    synchronized (object2) {
                        System.out.println(getName() + " synchronized w object2");
                    }
                }
            }
        };

        Thread tread2 = new Thread() {
            @Override
            public void run() {
                synchronized (object2) {
                    System.out.println(getName() + " synchronized w object2");
                    System.out.println(getName() + " do somethings");
                    for (long i = 0; i < Long.MAX_VALUE; i++) {
                        int a = Integer.MAX_VALUE;
                    }
                    synchronized (object1) {
                        System.out.println(getName() + " synchronized w object1");
                    }
                }
            }
        };
    }
}
