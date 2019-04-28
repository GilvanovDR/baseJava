package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    Map<String, Resume> storage = new HashMap<>();

    //========done

    @Override
    public void save(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void delete(String uuid) {
        storage.remove(uuid);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Resume[] getAll() {
        Resume[] result = new Resume[storage.size()];
        int i = 0;
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            result[i] = entry.getValue();
            i++;
        }
        return result;
    }

    @Override
    public Resume get(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void update(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    //========do
}
