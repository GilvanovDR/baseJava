package ru.GilvanovDr.WebApp.model;

/*
 * GilvanovDR 2019.
 */

import java.io.Serializable;

public enum SectionType implements Serializable {
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private static final long serialVersionUID = 1L;
    private final String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
