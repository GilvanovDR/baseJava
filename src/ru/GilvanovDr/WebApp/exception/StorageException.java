package ru.GilvanovDr.WebApp.exception;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(Exception e) {
        this(e.getMessage(), null, e);
    }

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message, e);
        this.uuid = uuid;
    }
}
