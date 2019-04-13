package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    static final private int STORAGE_SIZE = 10000;
    protected Resume[] storage = new Resume[STORAGE_SIZE];
    protected int size = 0;

    abstract protected int getIndex(String uuid);

    public abstract void save(Resume r);

    abstract public void delete(String uuid);

    public void update(Resume resume) {
        int item = getIndex(resume.getUuid());
        if (item >= 0) {
            storage[item] = new Resume(resume.getUuid());
        } else {
            System.out.println("Resume " + resume.getUuid() + " is not found in storage");
        }
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int item = getIndex(uuid);
        if (item >= 0) {
            return storage[item];
        } else {
            System.out.println("Resume " + uuid + " is not found in storage");
            return null;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

}
