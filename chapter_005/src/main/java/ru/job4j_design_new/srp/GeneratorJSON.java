package ru.job4j_design_new.srp;

import java.util.function.Predicate;

public class GeneratorJSON implements Generator {

    private Store store;

    public GeneratorJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        StringBuilder text = new StringBuilder()
                .append("{")
                .append(System.lineSeparator());
        for (Employer employee : store.findBy(filter)) {
            text.append("name: ").append(employee.getName())
                    .append(System.lineSeparator())
                    .append("hired: ").append(employee.getHired())
                    .append(System.lineSeparator())
                    .append("fired: ").append(employee.getFired())
                    .append(System.lineSeparator())
                    .append("salary: ").append(employee.getSalary()).append("рублей")
                    .append(System.lineSeparator())
                    .append("{")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
