package ru.job4j_design_new.srp;

import java.util.function.Predicate;

public class AccountingReport implements Report {
    private Generator generator;

    public AccountingReport(Generator generator) {
        this.generator = generator;
    }

    @Override
    public String getReport(Predicate<Employer> filter) {
        return generator.generate(filter);
    }
}

