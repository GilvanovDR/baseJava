package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;

public class ListStorage extends AbstractStorage {
    Collection<Resume> storage = new ArrayList<>();

    //========done
    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public void save(Resume r) {
        storage.add(r);
    }

    //========do
    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {
    }

    @Override
    public void update(Resume resume) {

    }
}
