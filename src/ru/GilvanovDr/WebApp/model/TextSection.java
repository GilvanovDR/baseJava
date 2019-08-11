package ru.GilvanovDr.WebApp.model;

/*
 * GilvanovDR 2019.
 */

import java.util.Objects;

public class TextSection extends Section {
    private final String content;

    public TextSection(String content) {
        Objects.requireNonNull(content,"content must be not Null");
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
