package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", resume.getUuid(), e);
        }
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

    protected abstract void doWrite(Resume r, File file) throws IOException;

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("IO error", doRead(file).getUuid());
        }
    }

    @Override
    protected Resume doGet(File file) {
        //abstract doRead
        return doRead(file);
    }

    @Override
    protected List<Resume> getStorageAsList() {
        List<Resume> list = new ArrayList<>();
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            list.add(doRead(file));
        }

        // abstract doRead from file
        return list;
    }

    protected abstract Resume doRead(File file);

    @Override
    public int size() {
        int counter = 0;
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                counter++;
            }
        }
        //посчитать сколько файлов в каталоге
        return counter;
    }

    @Override
    public void clear() {
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                if (file.delete()) {
                    throw new StorageException("IO error", doRead(file).getUuid());
                }
            }
            //удалить все из каталога
        }
    }
}
