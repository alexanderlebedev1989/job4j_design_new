package ru.job4j.search_by_criteria;

import java.util.HashMap;
import java.util.Map;

public class ArgKeys {

    private final String MASK = "m";
    private final String REGEX = "r";
    private final String FULL = "f";
    private final String DIRECTORY = "d";
    private final String NAME = "n";
    private final String OUTPUT = "o";

    private int countArguments = 7;
    private String[] args;
    private Map<String, String> values = new HashMap<>();

    public Map<String, String> getValues() {
        return values;
    }

    public String getMASK() {
        return MASK;
    }
    public String getREGEX() {
        return REGEX;
    }
    public String getFULL() {
        return FULL;
    }

    public ArgKeys(String[] args) {
        this.args = args;
    }

    public String directory() {
        return check(DIRECTORY);
    }
    public String name() {
        return check(NAME);
    }
    public String output() {
        return check(OUTPUT);
    }

    public void parse(String[] args) {
        if (args.length < countArguments) {
            throw new IllegalArgumentException("There should be three keys");
        }
        for (int index = 0; index < args.length; index++) {
            if (args[index].substring(1).equals(MASK)
                    || (args[index].substring(1).equals(REGEX)
                    || (args[index].substring(1).equals(FULL)))) {
                values.put(args[index].substring(1), null);
            } else if (args[index].substring(1).equals(NAME)
                    || (args[index].substring(1).equals(OUTPUT)
                    || (args[index].substring(1).equals(DIRECTORY))))
                values.put(args[index].substring(1), args[index + 1]);
            }
        }

    public String check(String arg) {
        if (!values.containsKey(arg))
            throw new IllegalArgumentException("specify the correct keys: -d - search directory; " +
                    "-n - file name (full name, mask, regular expression" +
                    "-f - search by name, -m - search by mask, -f - search by regular expression; " +
                    "-o - file to write");
        return values.get(arg);
    }
}
