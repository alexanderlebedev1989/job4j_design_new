package ru.job4j_design_new.cache;

public class Starter {
    public static void main(String[] args) {
        String path = "tests.txt";
        MyCache myCache = new MyCache();
        String result = myCache.getCache(path);
        System.out.println(result);
    }
}
