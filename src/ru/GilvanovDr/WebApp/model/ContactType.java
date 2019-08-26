package ru.GilvanovDr.WebApp.model;
/*
 * GilvanovDR 2019.
 */

import java.io.Serializable;

public enum ContactType implements Serializable {

    PHONE("Телефон"),
    MOBILE("Мобильный"),
    HOME_PHONE("Домшний телфон"),
    SKYPE("Skype"),
    MAIL("Почта"),
    LINKEDID("Профиль LinkedID"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOME_PAGE("Домашняя страница");

    private static final long serialVersionUID = 1L;
    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
