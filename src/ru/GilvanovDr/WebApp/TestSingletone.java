package ru.GilvanovDr.WebApp;

public class TestSingletone {
    private static TestSingletone ourInstance = new TestSingletone();

    public static TestSingletone getInstance() {
        return ourInstance;
    }

    private TestSingletone() {
    }
}
