package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;

public abstract class SortedArrayStorage implements Storage {
    static final private int STORAGE_SIZE = 10000;
    private Resume[] storage = new Resume[STORAGE_SIZE];
    private int size = 0;

    public int size() {
        return size;
    }
}
