package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    static final private int STORAGE_SIZE = 10000;
    protected Resume[] storage = new Resume[STORAGE_SIZE];
    protected int size = 0;

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

    abstract protected int getIndex(String uuid);
}
