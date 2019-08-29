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

import ru.GilvanovDr.WebApp.storage.Strategy.DataStream;
import ru.GilvanovDr.WebApp.storage.Strategy.XmlStream;

/*
 * Create by GilvanovDR at 2019.
 *
 */

public class ObjectDataStorageTest extends AbstractStorageTest {
    public ObjectDataStorageTest() {
        super(new PathStorage(STORAGE_DIR, new DataStream()));
    }
}
