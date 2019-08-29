/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp.storage.Strategy;

import ru.GilvanovDr.WebApp.model.Resume;
import ru.GilvanovDr.WebApp.utils.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonStream implements SerializationStrategy {


    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            JsonParser.write(r, w);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return JsonParser.read(r,Resume.class);
        }
    }
}
