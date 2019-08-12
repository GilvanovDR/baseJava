package ru.GilvanovDr.WebApp.utils;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {
    private DateUtil() {
    }

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }
}
