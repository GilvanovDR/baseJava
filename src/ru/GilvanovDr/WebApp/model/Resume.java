package ru.GilvanovDr.WebApp.model;

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
        Resume resume = (Resume) obj;
        if (this.uuid == resume.uuid) {
            return true;
        } else {
            return false;
        }
    }
}
