package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./src/test/resources/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Petr Arsentev")
        );
    }

    @Test
    public void whenPairWithComment() {
        String path = "./src/test/resources/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name1"),
                is("Ivan Petrov")
        );

    }
}
