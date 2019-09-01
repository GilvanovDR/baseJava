package ru.GilvanovDr.WebApp.utils;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.GilvanovDr.WebApp.model.Section;

import java.io.Reader;
import java.io.Writer;


public class JsonParser {
    private static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Section.class, new JasonSectionAdapter())
            .create();

    public static <T> T read(Reader reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> void write(T object, Writer writer) {
        GSON.toJson(object, writer);
    }

}
