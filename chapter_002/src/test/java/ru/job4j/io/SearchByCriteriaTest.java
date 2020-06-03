package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.search_by_criteria.ArgKeys;
import ru.job4j.search_by_criteria.SearchByCriteria;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SearchByCriteriaTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test(expected = IllegalArgumentException.class)
    public void parseException() {
        String[] args = {"-m=.*txt", "-o=log.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
    }

    @Test
    public void parse() {
        String[] args = {"-d=c:\\projects\\job4j", "-m=.*txt", "-o=log.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        String result = argKeys.getValues().get("m");
        assertThat(result, is(".*txt"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkException() {
        String[] args = {"-t=c:\\projects\\job4j", "-m=.*txt", "-o=log.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        String result = argKeys.directory();
    }

    @Test
    public void check() {
        String[] args = {"-d=c:\\projects\\job4j", "-m=.*txt", "-o=log.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        String result = argKeys.check("m");
        assertThat(result, is(".*txt"));
    }

    @Test
    public void find() throws IOException {
        String[] args = {"-d=c:\\projects\\job4j", "-m=Min.java", "-o=test.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        SearchByCriteria searchByCriteria = new SearchByCriteria();
        List<File> files = searchByCriteria.find(argKeys);
        String expected = "Min.java";
        assertThat(files.get(0).getName(), is(expected));
    }

    @Test
    public void whenWriteThenFilesShouldBeSelected() throws IOException {
        String[] args = {"-d=c:\\projects\\job4j", "-m=Min.java", "-o=test.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        SearchByCriteria searchByCriteria = new SearchByCriteria();
        List<File> files = searchByCriteria.find(argKeys);
        searchByCriteria.write(files, argKeys);
        String expected = "c:\\projects\\job4j\\chapter_001\\src\\main\\java\\ru\\job4j\\array\\Min.java";
        try (BufferedReader reader = new BufferedReader(new FileReader(argKeys.output()))) {
            String result = reader.readLine();
            assertThat(result, is(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenRecordThenNothingMustBeFound() throws IOException {
        String[] args = {"-d=c:\\projects\\job4j", "-m=Mon.java", "-o=test.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        SearchByCriteria searchByCriteria = new SearchByCriteria();
        List<File> files = searchByCriteria.find(argKeys);
        searchByCriteria.write(files, argKeys);
        String expected = "File not found";
        try (BufferedReader reader = new BufferedReader(new FileReader(argKeys.output()))) {
            String result = reader.readLine();
            assertThat(result, is(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
