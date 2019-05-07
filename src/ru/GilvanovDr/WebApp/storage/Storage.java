package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.model.Resume;

import java.util.List;

public interface Storage {
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    List<Resume> getAllSorted();

    void update(Resume resume);

    void save(Resume r);

    void delete(String uuid);

    int size();

    Resume get(String uuid);

    void clear();


}
