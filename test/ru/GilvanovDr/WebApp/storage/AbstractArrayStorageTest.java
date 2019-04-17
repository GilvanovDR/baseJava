package ru.GilvanovDr.WebApp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.GilvanovDr.WebApp.exception.ExistStorageException;
import ru.GilvanovDr.WebApp.exception.NoExistStorageException;
import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;

import java.lang.reflect.Field;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public class AbstractArrayStorageTest {
    private Storage storage = new SortedArrayStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
        System.out.println("before");
    }


    @Test(expected = ExistStorageException.class)
    public void save() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = NoExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        storage.delete(UUID_1);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(UUID_1, storage.get(UUID_1).toString());
    }

    @Test(expected = NoExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void overFlow() throws NoSuchFieldException, IllegalAccessException {
        Field field = storage.getClass().getSuperclass().getDeclaredField("STORAGE_SIZE");
        field.setAccessible(true);
        int range = Integer.parseInt(field.get(storage).toString());
        for (int i = 0; i <= range; i++) {
            storage.save(new Resume("uuid" + i * 1000));
        }
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        storage.delete(UUID_2);
        Assert.assertEquals(2,storage.getAll().length);
    }
}