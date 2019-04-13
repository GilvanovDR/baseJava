package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume r = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, r);
    }

    @Override
    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Array is full, you need delete one or more Resume");
        } else {
            int index = getIndex(r.getUuid());
            if (index >= 0) {
                System.out.println("Resume " + r.getUuid() + " contained in storage");
            } else {
                index = -(index + 1);
                System.arraycopy(storage, index, storage, index + 1, size - index);
                storage[index] = r;
                size++;
            }

        }
    }

    @Override
    public void delete(String uuid) {
        final int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " is not found in storage");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            size--;
        }
    }
}
