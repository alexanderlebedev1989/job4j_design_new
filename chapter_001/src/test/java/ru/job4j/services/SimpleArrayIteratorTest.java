package ru.job4j.services;


import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayIteratorTest {

    @Test
    public void shouldReturnEvenNumbers() {
        Iterator<Integer> it = new SimpleArray<>(new Integer[]{1, 2, 3}).iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        Iterator<Integer> it = new SimpleArray<>(new Integer[]{1, 2, 3}).iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}
