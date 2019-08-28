package ru.GilvanovDr.WebApp.storage.Strategy;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import ru.GilvanovDr.WebApp.model.*;
import ru.GilvanovDr.WebApp.utils.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStream implements SerializationStrategy {
    private XmlParser xmlParser;

    public XmlStream() {
        this.xmlParser = new XmlParser(Link.class, ListSection.class
                , Organization.class, OrganizationSection.class
                , Resume.class, TextSection.class, Organization.Position.class);
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(r, w);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}
