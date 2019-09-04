/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final File PROPERTIES_FILE = new File("config/resume.properties");
    private static final Config INSTANCE = new Config();
    private String storageDir;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;



    public static Config get() {
        return INSTANCE;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getStorageDir() {
        return storageDir;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            properties.load(is);
            storageDir = properties.getProperty("storage.dir");
            dbUrl = properties.getProperty("db.url");
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");

        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPERTIES_FILE.getAbsolutePath());
        }
    }
}
