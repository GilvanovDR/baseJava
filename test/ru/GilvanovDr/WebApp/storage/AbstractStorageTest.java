package ru.GilvanovDr.WebApp.storage;

import org.junit.Before;
import org.junit.Test;
import ru.GilvanovDr.WebApp.exception.ExistStorageException;
import ru.GilvanovDr.WebApp.exception.NoExistStorageException;
import ru.GilvanovDr.WebApp.model.Resume;

import static org.junit.Assert.assertEquals;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public abstract class AbstractStorageTest {
    private Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }


    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }


    @Test(expected = NoExistStorageException.class)
    public void deleteNoExist() {
        storage.delete("dummy");
    }

    @Test(expected = NoExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test

    public void update() {
//        storage.update(new Resume("dummy"));
    }

    @Test(expected = NoExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NoExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() {
        storage.save(new Resume(UUID_1));
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
        assertEquals(RESUME_1, result[0]);
        assertEquals(RESUME_2, result[1]);
        assertEquals(RESUME_3, result[2]);


    }
}