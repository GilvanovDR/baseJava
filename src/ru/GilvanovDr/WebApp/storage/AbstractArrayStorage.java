package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.exception.ExistStorageException;
import ru.GilvanovDr.WebApp.exception.NoExistStorageException;
import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected final static int STORAGE_SIZE = 10000;
    protected Resume[] storage = new Resume[STORAGE_SIZE];
    int size = 0;

    @Override
    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void fillEmptyItem(int index);

    protected abstract void addNewResume(Resume r, int index);

    @Override
    public void save(Resume r) {
        if (size == storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            int index = getIndex(r.getUuid());
            if (index >= 0) {
                throw new ExistStorageException(r.getUuid());
            } else {
                addNewResume(r, index);
                size++;
            }
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NoExistStorageException(uuid);
        } else {
            fillEmptyItem(index);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    public void update(Resume resume) {
        int item = getIndex(resume.getUuid());
        if (item >= 0) {
            storage[item] = new Resume(resume.getUuid());
        } else {
            throw new NoExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int item = getIndex(uuid);
        if (item < 0) {
            throw new NoExistStorageException(uuid);
        } else {
            return storage[item];
        }
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}
