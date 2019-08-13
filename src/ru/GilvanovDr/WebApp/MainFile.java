package ru.GilvanovDr.WebApp;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        File file = new File("D:\\Java Project\\baseJava");
        getFileTree(file, 0);
    }

    private static void getFileTree(File file, int i) {
        for (int j = 0; j < i; j++) {
            System.out.print("\t");
        }
        if (file.isDirectory()) {
            i++;
            System.out.println(file.getName() + ":");
            for (File file1 : Objects.requireNonNull(file.listFiles())) {
                getFileTree(file1, i);
            }

        } else {
            System.out.println(file.getName());
        }

    }


}
