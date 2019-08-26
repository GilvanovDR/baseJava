package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import com.sun.corba.se.spi.resolver.Resolver;
import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    public AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory or is not writable");
        }
    }

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

    @Override
    protected void doUpdate(Resume resume, Path path) {

    }

    @Override
    protected boolean isExist(Path path) {
        return false;
    }

    @Override
    protected void doSave(Resume r, Path path) {

    }

    @Override
    protected Path getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(),e);
        }

    }

    @Override
    protected Resume doGet(Path path) {
        return null;
    }

    @Override
    protected List<Resume> getStorageAsList() {
        ArrayList<Resume> list;
        try {
            list = Files.list(directory).map(this::doGet).collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new StorageException("Directory read error", null,e);
        }

        return list;
    }

    @Override
    public int size() {
        int count;
        try {
            count = (int) Files.lines(directory).count();
        } catch (IOException e) {
            throw new StorageException("Directory read error ", null, e);
        }
        return count;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null,e);
        }

    }
}
