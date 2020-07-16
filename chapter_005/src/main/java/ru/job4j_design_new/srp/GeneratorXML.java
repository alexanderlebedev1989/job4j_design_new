package ru.job4j_design_new.srp;

import java.util.function.Predicate;

public class GeneratorXML implements Generator {

    private Store store;

    public GeneratorXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        StringBuilder text = new StringBuilder()
                .append("<REPORT>")
                .append(System.lineSeparator())
                .append("<NAME>Name<NAME>;")
                .append("<HIRED>Hired<HIRED>;")
                .append("<FIRED>Fired<FIRED>;")
                .append("<SALARY>Salary<SALARY>;")
                .append(System.lineSeparator());
        for (Employer employee : store.findBy(filter)) {
            text.append("<NAME>").append(employee.getName()).append("<NAME>;")
                    .append("<HIRED>").append(employee.getHired()).append("<HIRED>;")
                    .append("<FIRED>").append(employee.getFired()).append("<FIRED>;")
                    .append("<SALARY>").append(employee.getSalary()).append("рублей").append("<SALARY>;")
                    .append("</REPORT>")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
