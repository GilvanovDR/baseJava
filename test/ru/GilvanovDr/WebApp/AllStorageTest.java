package ru.GilvanovDr.WebApp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.GilvanovDr.WebApp.storage.*;

/*
 * Create by GilvanovDR at 2019.
 *
 */

/*
 * Create by GilvanovDR at 2019.
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({StorageTest.class, SortedStorageTest.class,
        MapUuidStorageTest.class, MapResumeStorageTest.class, ListStorageTest.class,ObjectStreamStorageTest.class})
public class AllStorageTest {

}
