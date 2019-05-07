package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public class MapDateStorage extends AbstractStorage {
    private Map<Date, Resume> map = new HashMap();

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        map.put((Date) searchKey, resume);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey((Date) searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(new Date(), r);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove((Date) searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get((Date) searchKey);
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();

    }
}
