/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        System.out.println(minValue(new int[]{9, 8}));
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(i);
        }
        for (Integer item : oddOrEven(list)) {
            System.out.print(item + " ");
        }
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.size() % 2 == 0
                ?
                integers.stream().filter(item -> item % 2 == 0).collect(Collectors.toList())
                :
                integers.stream().filter(item -> item % 2 != 0).collect(Collectors.toList());
    }

    private static int minValue(int[] ints) {
        AtomicInteger counter = new AtomicInteger(0);
        int[] newInt = Arrays.stream(ints)
                .distinct()
                .sorted()
                .toArray();
        return Arrays.stream(ints)
                .map(item -> item *= (Math.pow(10, (newInt.length - counter.incrementAndGet()))))
                .sum();
    }
}

