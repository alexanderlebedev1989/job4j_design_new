package ru.job4j_design_new.srp;

import java.util.function.Predicate;

public interface Generator {
    String generate(Predicate<Employer> filter);
}
