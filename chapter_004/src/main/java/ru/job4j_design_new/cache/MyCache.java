package ru.job4j_design_new.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class MyCache implements Cache<String> {

    private Map<String, SoftReference<String>> cache = new HashMap<>();

    @Override
    public String getCache(String key) {
        if (!cache.containsValue(cache.get(key))) {
            addCache(key);
        }
        if (cache.get(key) == null) {
            addCache(key);
        }
        return cache.get(key).get();
    }

    @Override
    public void addCache(String key) {
        String result = read(key);
        SoftReference<String> strSoft = new SoftReference<>(result);
        cache.put(key, strSoft);
    }

    public String read(String key) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(key))) {
            reader.lines().forEach(sj::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sj.toString();
    }
}
