package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected Storage storage;

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void clear() {

    }

    int size = 0;

    @Override
    public int size() {
        return size;
    }
}