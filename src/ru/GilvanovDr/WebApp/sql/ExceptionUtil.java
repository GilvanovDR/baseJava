/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp.sql;

import ru.GilvanovDr.WebApp.exception.ExistStorageException;
import ru.GilvanovDr.WebApp.exception.StorageException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }
    public static StorageException converException(SQLException e) {
        if ("23505".equals(e.getSQLState())) {
            throw new ExistStorageException(null);
        }
        return new StorageException(e);
    }
}
