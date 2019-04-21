package ru.GilvanovDr.WebApp.storage;

import org.junit.Before;
import org.junit.Test;
import ru.GilvanovDr.WebApp.exception.ExistStorageException;
import ru.GilvanovDr.WebApp.exception.NoExistStorageException;
import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
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
        assertSize(3);
    }

    @Test
    public void get() {
        assertEquals(UUID_1, storage.get(UUID_1).toString());
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
        assertSize(0);
    }

    @Test
    public void getAll() {
        Resume[] result = storage.getAll();
        assertEquals(3, result.length);
        assertEquals(RESUME_1,result[0]);
        assertEquals(RESUME_2,result[1]);
        assertEquals(RESUME_3,result[2]);


    }
}