package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;
import ru.GilvanovDr.WebApp.storage.Strategy.SerializationStrategy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private SerializationStrategy objectStream;

    PathStorage(String dir, SerializationStrategy objectStream) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory or is not writable");
        }
        this.objectStream = objectStream;
        Objects.requireNonNull(objectStream, "ObjectStream of serialization must be not null");
    }

    //запись в поток
    //  public abstract void doWrite(Resume r, OutputStream os) throws IOException; delegate to objectStream.class

    //чтение из потока
    //  public abstract Resume doRead(InputStream is) throws IOException; delegate to objectStream.class

    @Override
    //обновлени резюме в файле
    protected void doUpdate(Resume resume, Path path) {
        try {
            objectStream.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Can't write file", resume.getUuid(), e);
        }
    }

    @Override
    //чтения резюме из файла
    protected Resume doGet(Path path) {

        try {
            return objectStream.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File read error", path.toString(), e);
        }
    }

    @Override
    //НАличие файла
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    //создание нового пустого фала с послеующей перезаписью
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Can't create new file", path.toString(), e);
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
        List<Resume> list;
        try {
            list = Files.list(directory).map(this::doGet).collect(Collectors.toList());
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
