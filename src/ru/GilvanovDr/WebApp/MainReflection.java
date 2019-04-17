package ru.GilvanovDr.WebApp;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.model.Resume;
import ru.GilvanovDr.WebApp.storage.ArrayStorage;
import ru.GilvanovDr.WebApp.storage.Storage;

import java.lang.reflect.Field;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume r = new Resume("uui1");
        Storage stor = new ArrayStorage();
        System.out.println((stor));
        System.out.println(r);
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.get(r));
        field.set(r,"sdf");
        System.out.println(field.get(r));
        //TODO: invoke r.toString via Reflection
    }

}
