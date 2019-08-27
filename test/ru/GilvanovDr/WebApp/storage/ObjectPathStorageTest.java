package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.storage.Strategy.ObjectStreamSerialization;

public class ObjectPathStorageTest extends AbstractStorageTest {
    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR,new ObjectStreamSerialization()));
    }
}
