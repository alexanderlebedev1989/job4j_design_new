package ru.job4j.io;

import org.junit.Test;
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

    @Test(expected = IllegalArgumentException.class)
    public void parseException() {
        String[] args = {"-n", ".*txt", "-o", "log.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
    }

    @Test
    public void parse() {
        String[] args = {"-d", "./src/main/java/ru/job4j/io", "-n", "Abuse.java", "-f", "-o", "test.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        String result = argKeys.getValues().get("n");
        assertThat(result, is("Abuse.java"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkException() {
        String[] args = {"-p", "./src/main/java/ru/job4j/io", "-n", "Abuse.java", "-f", "-o", "test.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        String result = argKeys.directory();
    }

    @Test
    public void check() {
        String[] args = {"-d", "./src/main/java/ru/job4j/io", "-n", "Abuse.java", "-f", "-o", "test.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        String result = argKeys.check("n");
        assertThat(result, is("Abuse.java"));
    }

    @Test
    public void find() throws IOException {
        String[] args = {"-d", "./src/main/java/ru/job4j/io", "-n", "Abuse.java", "-f", "-o", "test.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        SearchByCriteria searchByCriteria = new SearchByCriteria();
        List<File> files = searchByCriteria.find(argKeys);
        String expected = "Abuse.java";
        assertThat(files.get(0).getName(), is(expected));
    }

    @Test
    public void whenWriteThenFilesShouldBeSelectedByFullName() throws IOException {
        String[] args = {"-d", "./src/main/java/ru/job4j/io", "-n", "Abuse.java", "-f", "-o", "test.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        SearchByCriteria searchByCriteria = new SearchByCriteria();
        List<File> files = searchByCriteria.find(argKeys);
        searchByCriteria.write(files, argKeys);
        String expected = "Abuse.java";
        try (BufferedReader reader = new BufferedReader(new FileReader(argKeys.output()))) {
            String result = reader.readLine();
            assertThat(result, is(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenWriteThenFilesShouldBeSelectedByMask() throws IOException {
        String[] args = {"-d", "./src/main/java/ru/job4j/chat", "-n", "java", "-m", "-o", "test.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        SearchByCriteria searchByCriteria = new SearchByCriteria();
        List<File> files = searchByCriteria.find(argKeys);
        searchByCriteria.write(files, argKeys);
        String[] expected = {"ConsoleChat.java", "Starter.java"};
        try (BufferedReader reader = new BufferedReader(new FileReader(argKeys.output()))) {
            String result;
            int index = 0;
            while ((result = reader.readLine()) != null)
                assertThat(result, is(expected[index++]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenWriteThenFilesShouldBeSelectedByRegEx() throws IOException {
        String[] args = {"-d", "./src/main/java/ru/job4j/io", "-n", "A.*java", "-r", "-o", "test.txt"};
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        SearchByCriteria searchByCriteria = new SearchByCriteria();
        List<File> files = searchByCriteria.find(argKeys);
        searchByCriteria.write(files, argKeys);
        String[] expected = {"Abuse.java", "Analizy.java", "ArgsName.java", "ArrayMatrice.java"};
        try (BufferedReader reader = new BufferedReader(new FileReader(argKeys.output()))) {
            String result;
            int index = 0;
            while ((result = reader.readLine()) != null)
                assertThat(result, is(expected[index++]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenRecordThenNothingMustBeFound() throws IOException {
        String[] args = {"-d", "./src/main/java/ru/job4j/io", "-n", "Mon.java", "-f", "-o", "test.txt"};
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
