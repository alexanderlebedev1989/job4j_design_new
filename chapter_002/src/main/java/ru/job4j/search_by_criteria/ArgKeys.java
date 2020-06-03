package ru.job4j.search_by_criteria;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgKeys {

    private String[] args;

    private Map<String, String> values = new HashMap<>();

    public Map<String, String> getValues() {
        return values;
    }

    public ArgKeys(String[] args) {
        this.args = args;
    }

    public String directory() {
        return check("d");
    }

    public String maskName() {
        return check("m");
    }

    public String output() {
        return check("o");
    }

    public void parse(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("There should be three keys");
        }
        Arrays.stream(args)
                .map(s -> s.split("="))
                .forEach(strings -> values.put(strings[0].substring(1), strings[1]));
    }

    public String check(String arg) {
        if (!values.containsKey(arg))
            throw new IllegalArgumentException("specify the correct keys: -d - search directory: " +
                    "-m - search by name, mask, regular expression; " +
                    "-o - file to write");
        return values.get(arg);
    }
}
