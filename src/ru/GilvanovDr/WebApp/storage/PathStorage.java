package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private Strategy strategy;

    public PathStorage(String dir, Strategy strategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory or is not writable");
        }
        this.strategy = strategy;
        Objects.requireNonNull(strategy, "Strategy of serialization must be not null");
    }

    //запись в поток
    //  public abstract void doWrite(Resume r, OutputStream os) throws IOException; delegate to strategy.class

    //чтение из потока
    //  public abstract Resume doRead(InputStream is) throws IOException; delegate to strategy.class

    @Override
    //обновлени резюме в файле
    protected void doUpdate(Resume resume, Path path) {
        try {
            strategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("Can't write file", resume.getUuid(), e);
        }
    }

    @Override
    //чтения резюме из файла
    protected Resume doGet(Path path) {

        try {
            return strategy.doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("File read error", path.toString(), e);
        }
    }

    @Override
    //НАличие файла
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    //создание нового пустого фала с послеующей перезаписью
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Can't create new file", null, e);
        }
        doUpdate(r, path);
    }

    @Override
    //Передача ссылки на новый файл
    protected Path getSearchKey(String uuid) {
        return Paths.get(directory.toString(), uuid);

    }

    @Override
    //Удаления 1 файла
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
    }

    @Override
    //все фалы из директории в List<Resume>
    protected List<Resume> getStorageAsList() {
        ArrayList<Resume> list;
        try {
            list = Files.list(directory).map(this::doGet).collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new StorageException("Directory read error", null, e);
        }

        return list;
    }

    @Override
    //количество файлов в директории
    public int size() {
        int count;
        try {
            count = (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Directory read error ", null, e);
        }
        return count;
    }

    @Override
    //Очитска директории
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }
}
