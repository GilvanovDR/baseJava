package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void update(Resume resume) {
        int item = getIndex(resume.getUuid());
        if (item >= 0) {
            storage[item] = new Resume(resume.getUuid());
        } else {
            System.out.println("Resume " + resume.getUuid() + " is not found in storage");
        }
    }

    @Override
    public void save(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Resume " + r.getUuid() + " contained in storage");
        } else if (size == storage.length) {
            System.out.println("Array is full, you need delete one or more Resume");
        } else {
            storage[size] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int item = getIndex(uuid);

        if (item >= 0) {
            size--;
            storage[item] = storage[size];
            storage[size] = null;
        } else {
            System.out.println("Resume " + uuid + " is not found in storage");
        }
    }


}
