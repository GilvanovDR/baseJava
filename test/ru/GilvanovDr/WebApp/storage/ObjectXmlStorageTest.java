package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.storage.Strategy.XmlStream;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public class ObjectXmlStorageTest extends AbstractStorageTest {
    public ObjectXmlStorageTest() {
        super(new PathStorage(STORAGE_DIR, new XmlStream()));
    }
}
