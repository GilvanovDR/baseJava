package ru.GilvanovDr.WebApp;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import java.time.LocalDateTime;
import java.util.Date;

public class MainDate {
    public static void main(String[] args) throws InterruptedException {
        LocalDateTime date1 = LocalDateTime.now();
        System.out.println(date1);
        LocalDateTime date2 = LocalDateTime.now();
        System.out.println(date2);
        System.out.println(date1 == date2);
        System.out.println(date1.hashCode());
        System.out.println(date2.hashCode());
        //Thread.sleep(1);
        Date date3 = new Date();
        System.out.println(date3.hashCode());
        Long m1 = System.currentTimeMillis();
        Long m2 = System.currentTimeMillis();
        System.out.println(m1.hashCode());
        System.out.println(m2.hashCode());
        Thread.sleep(1);
        Long m3 = System.currentTimeMillis();
        System.out.println(m3.hashCode());
    }
}
