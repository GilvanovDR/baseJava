package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    final static int STORAGE_SIZE = 10000;
    protected Resume[] storage = new Resume[STORAGE_SIZE];
    int size = 0;

    protected abstract void addNewResume(Resume r, int index);

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void fillEmptyItem(int index);

    @Override
    protected List<Resume> getStorageAsList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void doUpdate(Resume resume, Object item) {
        storage[(Integer) item] = resume;
    }

    @Override
    protected void doSave(Resume r, Object index) {
        if (size == STORAGE_SIZE) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            addNewResume(r, (Integer) index);
            size++;
        }
    }

    @Override
    protected void doDelete(Object index) {
        fillEmptyItem((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume doGet(Object item) {
        return storage[(Integer) item];
    }

    @Override
    protected boolean isExist(Object item) {
        return (Integer) item >= 0;
    }


}
