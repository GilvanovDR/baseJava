package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;

import java.util.*;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public class MapDateStorage extends AbstractStorage {
    private Map<Long, Resume> map = new HashMap<>();

    private Long searchKeyAsLong(Object searchKey) {
        return (Long) searchKey;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        map.put(searchKeyAsLong(searchKey), resume);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKeyAsLong(searchKey));
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(System.currentTimeMillis(), r);
    }

    @Override
    protected Long getSearchKey(String uuid) {
        for (Map.Entry<Long, Resume> entry : map.entrySet()) {
            if (entry.getValue().getUuid().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKeyAsLong(searchKey));
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKeyAsLong(searchKey));
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(map.values());
        list.sort(RESUME_COMPARATOR);
        return list;
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
