package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Array is full, you need delete one or more ru.GilvanovDr.WebApp.model.Resume");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                size--;
                storage[size] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] storageWoNull = new Resume[size];
        System.arraycopy(storage, 0, storageWoNull, 0, size);
        return storageWoNull;
    }

    public int size() {
        return size;
    }
}
