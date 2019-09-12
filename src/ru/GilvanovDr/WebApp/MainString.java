/*
 * Create by GilvanovDR at 2019.
 *
 */

package ru.GilvanovDr.WebApp;

import java.nio.charset.Charset;
import java.util.*;

public class MainString {

    public static void main(String[] args) {
        System.out.println(testList());
        SortedMap<String, Charset> charsetsMap = Charset.availableCharsets();
        System.out.println("Charsets available: " + charsetsMap.size());
        for (String name : charsetsMap.keySet()) {
            Charset charset = charsetsMap.get(name);
            StringBuffer sb = new StringBuffer();
            sb.append(name);
            sb.append(" (");
            for (Iterator<String> aliases = charset.aliases().iterator(); aliases.hasNext(); ) {
                sb.append(aliases.next());
                if (aliases.hasNext())
                    sb.append(",");
            }
            sb.append(")");
            System.out.println(sb.toString());
        }
    }

    private static Map<String, Integer> testList() {
        Map<String, Integer> result = new TreeMap<>(Comparator.comparing(String::length));
        result.put("one",1);
        result.put("two",2);
        result.put("sixteen",16);
        result.put("unlucky",13);
        return result;
    }
}
