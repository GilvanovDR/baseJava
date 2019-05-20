package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.exception.ExistStorageException;
import ru.GilvanovDr.WebApp.exception.NoExistStorageException;
import ru.GilvanovDr.WebApp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    static final Comparator<Resume> RESUME_UUID_COMPARATOR = Comparator.comparing(Resume::getUuid);
    private static final Comparator<Resume> RESUME_FULL_NAME_COMPARATOR = Comparator.comparing(Resume::getFullName);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doDelete(SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract List<Resume> getStorageAsList();

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getStorageAsList();
        list.sort(RESUME_FULL_NAME_COMPARATOR.thenComparing(RESUME_UUID_COMPARATOR));
        return list;
    }

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NoExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public void save(Resume r) {
        SK searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public void update(Resume resume) {
        SK searchKey = getExistedSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }
}
