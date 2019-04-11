package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;

public interface Storage {
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();

    void update(Resume resume);

    void save(Resume r);

    void delete(String uuid);

    int size();

    Resume get(String uuid);

    void clear();


}
