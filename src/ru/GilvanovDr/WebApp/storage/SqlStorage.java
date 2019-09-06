/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.exception.ExistStorageException;
import ru.GilvanovDr.WebApp.exception.NoExistStorageException;
import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;
import ru.GilvanovDr.WebApp.sql.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    private final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public List<Resume> getAllSorted() {
        try (Connection con = connectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM resume")) {
            ResultSet rs = ps.executeQuery();
            List<Resume> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
            }
            return list;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    // todo  Use SqlException code! remove doDelete, doSave
    @Override
    public void update(Resume resume) {
        if (!isExist(resume.getUuid())) {
            throw new NoExistStorageException(resume.getUuid());
        } else {
            try (Connection con = connectionFactory.getConnection();
                 PreparedStatement ps = con.prepareStatement("UPDATE resume SET full_name=? WHERE uuid=?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                ps.execute();
            } catch (SQLException e) {
                throw new StorageException(e);
            }
        }
    }

    @Override
    public void save(Resume r) {
        try (Connection con = connectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new ExistStorageException(r.getUuid());
            }
            throw new StorageException(e);
        }
    }

    private boolean isExist(String uuid) {
        try (Connection con = connectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT EXISTS(SELECT uuid FROM resume WHERE uuid = ?)")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new StorageException("Can't read from db", null);
            } else {
                return rs.getBoolean("exists");
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {
        if (!isExist(uuid)) {
            throw new NoExistStorageException(uuid);
        } else {
            try (Connection con = connectionFactory.getConnection();
                 PreparedStatement ps = con.prepareStatement("DELETE FROM resume WHERE uuid=?")) {
                ps.setString(1, uuid);
                ps.execute();

            } catch (SQLException e) {
                throw new StorageException(e);
            }
        }
    }

    @Override
    public int size() {
        try (Connection con = connectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM resume")) {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new StorageException("Can't read from db", null);
            } else {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Resume get(String uuid) {
        try (Connection con = connectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM resume r WHERE r.uuid=?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NoExistStorageException(uuid);
            } else {
                return new Resume(uuid, rs.getString("full_name"));
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
