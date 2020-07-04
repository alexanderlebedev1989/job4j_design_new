package ru.job4j.search;

import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgZip {

    private final String[] args;

    private final Map<String, String> values = new HashMap<>();

    public ArgZip(String[] args) {
        this.args = args;
    }

    public String directory() {
        return check("d");
    }

    public String exclude() {
        return check("e");
    }

    public String output() {
        return check("o");
    }

    public void parse(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException();
        }
        Arrays.stream(args)
                .map(s -> s.split("="))
                .forEach(strings -> values.put(strings[0].substring(1), strings[1]));
    }

    public String check(String arg) {
        if (!values.containsKey(arg)) throw new IllegalArgumentException();
        return values.get(arg);
    }
}



