package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    private final static Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected void addNewResume(Resume r, int index) {
        index = -(index + 1);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    protected void fillEmptyItem(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume r = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, r, RESUME_COMPARATOR);
    }
}
