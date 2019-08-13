package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must be not Null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " can't read/write");
        }
        this.directory = directory;
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        //тоже самое что и doSave только без создания фала
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            if (file.createNewFile()) {
                doWrite(r, file);
            }
        } catch (IOException e) {
            throw new StorageException("IO error", r.getUuid(), e);
        }
    }

    abstract void doWrite(Resume r, File file) throws IOException;

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Can't delete file", file.getName());
        }
    }

    @Override
    protected Resume doGet(File file) {
        //abstract doRead
        return null;
    }

    @Override
    protected List<Resume> getStorageAsList() {
        // abstract doRead from file
        return null;
    }

    @Override
    public int size() {
        //посчитать сколько файлов в каталоге
        return 0;
    }

    @Override
    public void clear() {
        //удалить все из каталога
    }
}
