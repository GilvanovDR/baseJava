package ru.GilvanovDr.WebApp;

import ru.GilvanovDr.WebApp.model.Resume;
import ru.GilvanovDr.WebApp.storage.SortedArrayStorage;
import ru.GilvanovDr.WebApp.storage.Storage;

/**
 * Test for your ru.GilvanovDr.WebApp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid3");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid1");
        Resume r4 = new Resume("uuid3");
        Resume r5 = new Resume("uuid5");

        System.out.println(r1.getUuid());
        ARRAY_STORAGE.save(r1);
        System.out.println(r2.getUuid());
        ARRAY_STORAGE.save(r2);
        System.out.println(r3.getUuid());
        ARRAY_STORAGE.save(r3);
        System.out.println(r4.getUuid());
        ARRAY_STORAGE.save(r4);

        System.out.println("\nGet r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        System.out.println("\nUpdate r1 r4(=r3) r5");
        System.out.println("uuid1 hashcode =" + ARRAY_STORAGE.get("uuid1").hashCode());
        ARRAY_STORAGE.update(new Resume("uuid1"));
        System.out.println("uuid1 hashcode(upd) =" + ARRAY_STORAGE.get("uuid1").hashCode());
        System.out.println("uuid3 hashcode =" + ARRAY_STORAGE.get("uuid3").hashCode());
        ARRAY_STORAGE.update(r4);
        System.out.println("uuid3 hashcode(upd) =" + ARRAY_STORAGE.get("uuid3").hashCode());
        ARRAY_STORAGE.update(r5);

        System.out.println();
        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        System.out.println("\ndell uuid1");
        printAll();
        System.out.println("\ndell All");
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    private static void printAll() {
        System.out.println("Get All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
