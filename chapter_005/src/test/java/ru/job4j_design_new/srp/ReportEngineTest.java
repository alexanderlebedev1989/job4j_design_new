package ru.job4j_design_new.srp;

import org.junit.Test;
import java.util.Calendar;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append("рублей")
                .append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportForProgrammers() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        store.add(worker);
        Report programmers = new ReportEngineProgrammers(store);
        StringBuilder expect = new StringBuilder()
                .append("<p>&quot;Name; Hired; Fired; Salary&quot;</p>")
                .append(System.lineSeparator())
                .append("<p>&quot;").append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append("рублей")
                .append("&quot;</p>")
                .append(System.lineSeparator());
        assertThat(programmers.generate(em -> true), is(expect.toString()));
    }
    @Test
    public void whenTheReportForAccounting() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 10);
        store.add(worker);
        Report accounting = new ReportEngineAccounting(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append("$").append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(accounting.generate(em -> true), is(expect.toString()));
    }
}
