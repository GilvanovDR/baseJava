/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp;

import java.util.concurrent.*;

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
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    mc.inc();
                }
                latch.countDown();
                return 5;
            });

            /*Thread trhred = new Thread(() -> {

            });
            trhred.start();*/

        }
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(counter);

        new DeadLock().startDeadLock();
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

        void startDeadLock() throws InterruptedException {
            tread2.start();
            tread1.start();
            Thread.sleep(500);
            System.out.println(tread1.getState());
            System.out.println(tread2.getState());
        }
    }
}
