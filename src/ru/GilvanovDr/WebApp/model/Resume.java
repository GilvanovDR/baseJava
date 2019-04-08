package ru.GilvanovDr.WebApp.model;

import java.util.Objects;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public boolean equals(Object obj) {
        return this.uuid == ((Resume) obj).uuid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
