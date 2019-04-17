package ru.GilvanovDr.WebApp.exception;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public class NoExistStorageException extends StorageException {
    public NoExistStorageException(String uuid) {
        super("Resume " + uuid + " is not found in storage", uuid);
    }
}
