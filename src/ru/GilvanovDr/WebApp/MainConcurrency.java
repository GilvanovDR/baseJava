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
        threads.forEach(t ->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Thread.sleep(500);
        System.out.println(counter);
    }

    private synchronized void inc() {
        counter++;
    }
}
