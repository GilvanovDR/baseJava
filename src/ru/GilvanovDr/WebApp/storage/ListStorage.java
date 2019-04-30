package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> storage = new ArrayList<>();

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

    @Override
    public Resume get(String uuid) {
        return storage.get(getIndex(uuid));
    }

    @Override
    protected void fillEmptyItem(int index) {

    }

    @Override
    protected void addNewResume(Resume r, int index) {

    }

    @Override
    public void delete(String uuid) {
        storage.remove(getIndex(uuid));
    }

    protected int getIndex(String uuid) {
        for (Resume res : storage) {
            if (res.getUuid().equals(uuid)) {
                return storage.indexOf(res);
            }
        }
        return -1;
    }

    @Override
    public void update(Resume resume) {
        storage.add(getIndex(resume.getUuid()), resume);
    }

    //========do


}
