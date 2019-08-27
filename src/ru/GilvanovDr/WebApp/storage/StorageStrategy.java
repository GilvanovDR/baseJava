package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

interface StorageStrategy {
    void doWrite(Resume r, OutputStream os) throws IOException;

    ;

    Resume doRead(InputStream is) throws IOException;

    ;
}
