package ru.GilvanovDr.WebApp;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainTestNIO {
    public static void main(String[] args) throws InterruptedException, IOException {
        Path path1 = Paths.get("D:\\Java Project\\baseJava\\src\\storage");
        Path path = Paths.get(path1.toString(), "uuid5");
        Files.createFile(path);
        Files.delete(path);
        System.out.println(Files.list(path1).count());
    }
}
