package ru.job4j_design_new.template;

import java.util.Map;

public interface Generator {
    String produce(String template, Map<String, String> args);
}
