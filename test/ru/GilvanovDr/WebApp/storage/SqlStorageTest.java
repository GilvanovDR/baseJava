/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.Config;

public class SqlStorageTest extends AbstractStorageTest {
   //TODO just SQLStorage d't use  getDbPassword getDbUser getDbUrl
    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getDbUrl()
                , Config.get().getDbUser()
                , Config.get().getDbPassword()));
    }
}