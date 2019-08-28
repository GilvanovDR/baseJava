package ru.GilvanovDr.WebApp.utils;

/*
 * Create by GilvanovDR at 2019.
 *
 */

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LacalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String str) throws Exception {
        return LocalDate.parse(str);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
