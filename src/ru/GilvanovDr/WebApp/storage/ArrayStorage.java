package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    private int storageContain(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(resume)) {
                return i;
            }
        }
        return -1;
    }

    private int storageContain(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void update(Resume resume) {
        int item = storageContain(resume);
        if (item >= 0) {
            storage[item] = new Resume(resume.getUuid());
        } else {
            System.out.println("Resume " + resume.getUuid() + " is not found in storage");
        }
    }

    public void clear() {
        Arrays.fill(storage,0,size,null);
        size = 0;
    }

    public void save(Resume r) {
        if (storageContain(r) >= 0) {
            System.out.println("Resume " + r.getUuid() + " contained in storage");
        } else if (size == storage.length) {
            System.out.println("Array is full, you need delete one or more Resume");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int item = storageContain(uuid);
        if (item >= 0) {
            return storage[item];
        } else {
            System.out.println("Resume " + uuid + " is not found in storage");
            return null;
        }
    }

    public void delete(String uuid) {
        int item = storageContain(uuid);
        if (item >= 0) {
            System.arraycopy(storage, item + 1, storage, item, size - item - 1);
            size--;
            storage[size] = null;
        } else {
            System.out.println("Resume " + uuid + " is not found in storage");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage,size);
    }

    public int size() {
        return size;
    }
}
