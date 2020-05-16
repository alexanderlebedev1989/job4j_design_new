package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Test
    public void test() {
        List<String> list = List.of("Начало периода: 10:58:01, конец периода: 10:59:01",
                                    "Начало периода: 11:01:02, конец периода: 11:02:02");
        Analizy analizy = new Analizy();
        String source = "./src/test/resources/server.log";
        String target = "./src/test/resources/unavailable.csv";
        analizy.unavailable(source, target);
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
