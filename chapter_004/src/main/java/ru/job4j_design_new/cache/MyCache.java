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
        String rsl = "";
        if (cache.containsValue(cache.get(key)) && cache.get(key).get() != null) {
            rsl = cache.get(key).get();
        } else {
            rsl = read(key);
            cache.put(key, new SoftReference<>(rsl));
        }
        return rsl;
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
