package ru.GilvanovDr.WebApp;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainFile {
    public static void main(String[] args) {
        File file = new File(".\\src\\ru\\GilvanovDr\\WebApp\\resource\\text.txt");
        /*System.out.println(file.getCanonicalPath());
        System.out.println(file.getCanonicalFile());
        File dir = new File("./src/ru/GilvanovDr/WebApp");
        System.out.println(Arrays.toString(dir.list()));*/
        try (InputStream fis = new FileInputStream(file)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
