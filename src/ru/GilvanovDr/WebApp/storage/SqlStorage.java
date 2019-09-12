/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp.storage;

import ru.GilvanovDr.WebApp.exception.NoExistStorageException;
import ru.GilvanovDr.WebApp.exception.StorageException;
import ru.GilvanovDr.WebApp.model.Resume;
import ru.GilvanovDr.WebApp.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume",ps->{
            ResultSet rs = ps.executeQuery();
            List<Resume> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
            }
            return list;
        });
    }

    // todo  Use SqlException code! remove doDelete, doSave
    @Override
    public void update(Resume resume) {
        if (!isExist(resume.getUuid())) {
            throw new NoExistStorageException(resume.getUuid());
        } else {
            sqlHelper.execute("UPDATE resume SET full_name=? WHERE uuid=?", ps -> {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                ps.execute();
                return null;
            });
        }
    }

    @Override
    public void save(Resume r) {
        sqlHelper.<Void>execute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return null;
        });
    }

    private boolean isExist(String uuid) {
        return sqlHelper.execute("SELECT EXISTS(SELECT uuid FROM resume WHERE uuid = ?)", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new StorageException("Can't read from db", null);
            } else {
                return rs.getBoolean("exists");
            }
        });
    }

    @Override
    public void delete(String uuid) {
        if (!isExist(uuid)) {
            throw new NoExistStorageException(uuid);
        } else {
            sqlHelper.execute("DELETE FROM resume WHERE uuid=?", ps -> {
                ps.setString(1, uuid);
                ps.execute();
                return null;
            });
        }
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new StorageException("Can't read from db", null);
            } else {
                return rs.getInt("count");
            }
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r WHERE r.uuid=?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NoExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }
}
