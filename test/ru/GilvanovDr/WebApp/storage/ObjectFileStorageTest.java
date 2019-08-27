package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.storage.Strategy.ObjectStreamSerialization;

import java.io.File;

public class ObjectFileStorageTest extends AbstractStorageTest {
    public ObjectFileStorageTest() {
        super(new FileStorage(new File(STORAGE_DIR),new ObjectStreamSerialization()));
    }
}
