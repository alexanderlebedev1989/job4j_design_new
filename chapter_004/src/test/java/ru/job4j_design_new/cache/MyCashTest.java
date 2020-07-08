package ru.job4j_design_new.cache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MyCashTest {

    @Test
    public void readFile() {
        MyCache myCache = new MyCache();
        String path = "./src/main/resources/test.txt";
        String result = myCache.read(path);
        String expected = "test";
        assertThat(result, is(expected));
    }

    @Test
    public void getCache() {
        MyCache myCache = new MyCache();
        String path = "./src/main/resources/test.txt";
        myCache.addCache(path);
        String result = myCache.getCache(path);
        String expected = "test";
        assertThat(result, is(expected));
    }
}
