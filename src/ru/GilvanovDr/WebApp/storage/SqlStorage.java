/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.exception.NoExistStorageException;
import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;
import ru.GilvanovDr.WebApp.sql.ConnectionFactory;

import java.sql.*;
import java.util.List;

public class SqlStorage implements Storage {
    private final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void save(Resume r) {
        try (Connection con = connectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
            ps.setString(1,r.getUuid());
            ps.setString(2,r.getFullName());
            ps.execute();

        } catch (SQLException e) {
            throw new StorageException(e);
        }

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Resume get(String uuid) {
        try (Connection con = connectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM resume r WHERE r.uuid=?")){
            ps.setString(1,uuid);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NoExistStorageException(uuid);
            } else {
                return new Resume(uuid,rs.getString("full_name"));
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void clear() {
        try (Connection con = connectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM resume")) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }

    }
}
