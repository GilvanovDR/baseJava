package ru.GilvanovDr.WebApp.storage.Strategy;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * Create by GilvanovDR at 2019.
 *
 */

interface SerializationStrategy {
    void doWrite(Resume r, OutputStream os) throws IOException;

    Resume doRead(InputStream is) throws IOException;
}
