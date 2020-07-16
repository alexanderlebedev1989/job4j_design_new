package ru.job4j_design_new.srp;

import java.util.function.Predicate;

public class GeneratorEngine implements Generator {
    private Store store;

    public GeneratorEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        StringBuilder text = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employer employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append("рублей").append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
