package ru.job4j.services;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    @Test
    public void add() {
        SimpleArray<Integer> sa = new SimpleArray<>(new Integer[2]);
        sa.add(4);
        sa.add(2);
        Integer result = sa.get(0);
        assertThat(result, is(4));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addException() {
        SimpleArray<Integer> sa = new SimpleArray<>(new Integer[2]);
        sa.add(4);
        sa.add(2);
        sa.add(1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddThenException() {
        SimpleArray<Integer> sa = new SimpleArray<>(new Integer[1]);
        sa.add(4);
        Integer result = sa.get(1);
    }

    @Test
    public void get() {
        SimpleArray<Integer> sa = new SimpleArray<>(new Integer[] {1, 2, 5});
        int index = 1;
        Integer result = sa.get(index);
        assertThat(result, is(2));
    }

    @Test
    public void set() {
        SimpleArray<Integer> sa = new SimpleArray<>(new Integer[] {1, 2, 5});
        sa.set(1, 3);
        Integer result = sa.get(1);
        assertThat(result, is(3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenSetThenException() {
        SimpleArray<Integer> sa = new SimpleArray<>(new Integer[] {1, 2, 5});
        int index = 3;
        Integer inNumber = 3;
        sa.set(index, inNumber);
    }

    @Test
    public void remove() {
        SimpleArray<Integer> sa = new SimpleArray<>(new Integer[] {1, 2, 3, 6, 7});
        int index = 2;
        Integer[] result = sa.remove(index);
        Integer[] expected = {1, 2, 6, 7, null};
        assertThat(result, is(expected));
    }
}
