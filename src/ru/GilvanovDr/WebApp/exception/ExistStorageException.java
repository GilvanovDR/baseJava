package ru.GilvanovDr.WebApp.exception;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " contained in storage", uuid);
    }
}
