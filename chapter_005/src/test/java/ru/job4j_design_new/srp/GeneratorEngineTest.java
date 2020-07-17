package ru.job4j_design_new.srp;

import org.junit.Test;
import java.util.Calendar;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GeneratorEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        store.add(worker);
        Generator engine = new GeneratorEngine(store);
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
    public void whenReportForProgrammersInXMLFormat() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        store.add(worker);
        Generator XML = new GeneratorXML(store);
        Report report = new ProgrammersReport(XML);
        StringBuilder expect = new StringBuilder()
                .append("<REPORT>")
                .append(System.lineSeparator())
                .append("<NAME>Name<NAME>;").append("<HIRED>Hired<HIRED>;")
                .append("<FIRED>Fired<FIRED>;").append("<SALARY>Salary<SALARY>;")
                .append(System.lineSeparator())
                .append("<NAME>").append(worker.getName()).append("<NAME>;")
                .append("<HIRED>").append(worker.getHired()).append("<HIRED>;")
                .append("<FIRED>").append(worker.getFired()).append("<FIRED>;")
                .append("<SALARY>").append(worker.getSalary()).append("рублей").append("<SALARY>;")
                .append("</REPORT>")
                .append(System.lineSeparator());
        assertThat(report.getReport(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportForProgrammersInHTMLFormat() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        store.add(worker);
        Generator HTML = new GeneratorHTML(store);
        Report report = new ProgrammersReport(HTML);
        StringBuilder expect = new StringBuilder()
                .append("<p>&quot;Name; Hired; Fired; Salary&quot;</p>")
                .append(System.lineSeparator())
                .append("<p>&quot;")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append("рублей")
                .append("&quot;</p>")
                .append(System.lineSeparator());
        assertThat(report.getReport(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportForProgrammersInJSONFormat() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        store.add(worker);
        Generator JSON = new GeneratorJSON(store);
        Report report = new ProgrammersReport(JSON);
        StringBuilder expect = new StringBuilder()
                .append("{")
                .append(System.lineSeparator())
                .append("name: ").append(worker.getName())
                .append(System.lineSeparator())
                .append("hired: ").append(worker.getHired())
                .append(System.lineSeparator())
                .append("fired: ").append(worker.getFired())
                .append(System.lineSeparator())
                .append("salary: ").append(worker.getSalary()).append("рублей")
                .append(System.lineSeparator())
                .append("{")
                .append(System.lineSeparator());
        assertThat(report.getReport(em -> true), is(expect.toString()));
    }



    @Test
    public void whenTheReportForAccountingInXMLFormat() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 10);
        store.add(worker);
        Generator XML = new GeneratorXML(store);
        Report report = new AccountingReport(XML);
        StringBuilder expect = new StringBuilder()
                .append("<REPORT>")
                .append(System.lineSeparator())
                .append("<NAME>Name<NAME>;").append("<HIRED>Hired<HIRED>;")
                .append("<FIRED>Fired<FIRED>;").append("<SALARY>Salary<SALARY>;")
                .append(System.lineSeparator())
                .append("<NAME>").append(worker.getName()).append("<NAME>;")
                .append("<HIRED>").append(worker.getHired()).append("<HIRED>;")
                .append("<FIRED>").append(worker.getFired()).append("<FIRED>;")
                .append("<SALARY>").append(worker.getSalary()).append("рублей").append("<SALARY>;")
                .append("</REPORT>")
                .append(System.lineSeparator());
        assertThat(report.getReport(em -> true), is(expect.toString()));
    }
}
