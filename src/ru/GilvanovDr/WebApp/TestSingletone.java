package ru.GilvanovDr.WebApp;

public class TestSingletone {
    private static TestSingletone ourInstance = new TestSingletone();

    private TestSingletone() {
    }

    public static TestSingletone getInstance() {
        return ourInstance;
    }
}
