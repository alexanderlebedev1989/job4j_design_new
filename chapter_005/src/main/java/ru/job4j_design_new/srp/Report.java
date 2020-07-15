package ru.job4j_design_new.srp;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employer> filter);
}
