package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;

import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage{
    protected ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume r, OutputStream oi) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(oi)) {
             objectOutputStream.writeObject(r);
             objectOutputStream.flush();
        }
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        Resume resume = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(is)){
            resume = (Resume) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Can't read file",null,e);
        }
        return resume;
    }
}
