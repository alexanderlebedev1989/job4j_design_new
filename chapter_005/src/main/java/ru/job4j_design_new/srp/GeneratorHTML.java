package ru.job4j_design_new.srp;

import java.util.function.Predicate;

public class GeneratorHTML implements Generator {

    private Store store;

    public GeneratorHTML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        StringBuilder text = new StringBuilder()
                .append("<p>&quot;Name; Hired; Fired; Salary&quot;</p>")
                .append(System.lineSeparator());
        for (Employer employee : store.findBy(filter)) {
            text.append("<p>&quot;")
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append("рублей")
                    .append("&quot;</p>")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
