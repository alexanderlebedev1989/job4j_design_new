package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void test() throws IOException {
        List<String> list = List.of("Начало периода: 10:58:01, конец периода: 10:59:01",
                                    "Начало периода: 11:01:02, конец периода: 11:02:02");
        Analizy analizy = new Analizy();
        File source =  folder.newFile("server.log");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            String line;
            int index = 0;
            while ((line = read.readLine()) != null) {
                assertThat(line, is(list.get(index)));
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
