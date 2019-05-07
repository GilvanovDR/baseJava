package ru.GilvanovDr.WebApp;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import java.util.Date;

public class MainDate {
    public static void main(String[] args) throws InterruptedException {
        Date date1 = new Date();
        Date date2 = new Date();
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date1 == date2);
        System.out.println(date1.hashCode());
        System.out.println(date2.hashCode());
        Thread.sleep(2);
        Date date3 = new Date();
        System.out.println(date3.hashCode());
        System.out.println(System.currentTimeMillis());
        Thread.sleep(1);
        System.out.println(System.currentTimeMillis());
    }
}
