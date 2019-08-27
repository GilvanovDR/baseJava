package ru.GilvanovDr.WebApp.storage.Strategy;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;

import java.io.*;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public class ObjectStreamSerialization implements SerializationStrategy {
    @Override
    public void doWrite(Resume r, OutputStream oi) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(oi)) {
            objectOutputStream.writeObject(r);
            objectOutputStream.flush();
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        Resume resume;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(is)) {
            resume = (Resume) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Can't read file", null, e);
        }
        return resume;
    }
}
