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
        return map.containsKey(searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(new Date(), r);
    }

    @Override
    protected Date getSearchKey(String uuid) {
        for (Map.Entry<Date, Resume> entry : map.entrySet()) {
            if (entry.getValue().getUuid().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey);
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
