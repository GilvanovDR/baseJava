/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLTransaction<T> {
    T execute(Connection conn) throws SQLException;
}
