package ru.job4j.io;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "C:\\projects\\job4j_design_new\\data\\pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Petr Arsentev")
        );
    }

    @Test
    public void whenPairWithComment() {
        String path = "C:\\projects\\job4j_design_new\\data\\pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name1"),
                is("Ivan Petrov")
        );

    }
}
