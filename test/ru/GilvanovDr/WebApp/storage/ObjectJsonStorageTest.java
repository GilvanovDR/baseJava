/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp.storage;

/*
 * Create by GilvanovDR at 2019.
 *
 */

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.storage.Strategy.JsonStream;
import ru.GilvanovDr.WebApp.storage.Strategy.XmlStream;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public class ObjectJsonStorageTest extends AbstractStorageTest {
    public ObjectJsonStorageTest() {
        super(new PathStorage(STORAGE_DIR, new JsonStream()));
    }
}
