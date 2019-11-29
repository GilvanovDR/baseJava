/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp.sql;

import ru.GilvanovDr.WebApp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        execute(sql, PreparedStatement::execute);
    }

    public <T> T execute(String sql, SqlExecutor<T> executor) {
        try (Connection con = connectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw ExceptionUtil.converException(e);
        }
    }

    public <T> T transactionalExecute(SQLTransaction<T> executor) {
        try (Connection conn = connectionFactory.getConnection()) {
            try {
                conn.setAutoCommit(false);
                T res = executor.execute(conn);
                conn.commit();
                return res;
            } catch (SQLException e) {
                conn.rollback();
                throw ExceptionUtil.converException(e);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}