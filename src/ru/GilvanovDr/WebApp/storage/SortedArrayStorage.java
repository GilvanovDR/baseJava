package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    public void update(Resume resume) {
    }

    @Override
    public void save(Resume r) {
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
