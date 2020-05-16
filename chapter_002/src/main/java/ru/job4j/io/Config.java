package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            List<String> list = new ArrayList<>();
            read.lines().filter(s -> s.contains("=")).forEach(list::add);
            for (String s : list) {
                 String[] strings = s.split("=");
                 values.put(strings[0], strings[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().filter(s -> s.contains("=")).forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

//    public static void main(String[] args) {
//        Config config1 = new Config("./data/pair_without_comment.properties");
//        Config config2 = new Config("./data/pair_with_comment.properties");
//        System.out.println(config1.toString());
//        System.out.println();
//        System.out.println(config2.toString());
//    }
}
