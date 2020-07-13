package ru.job4j_design_new.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxMinTest {
    @Test
    public void max() {
        List<Integer> values = List.of(5, 7, 1, 3, 11);
        MaxMin maxMin = new MaxMin();
        Integer result = maxMin.max(values, Comparator.naturalOrder());
        Integer expected = 11;
        assertThat(result, is(expected));
    }

    @Test
    public void min() {
        List<Integer> values = List.of(5, 7, 1, 3, 11);
        MaxMin maxMin = new MaxMin();
        Integer result = maxMin.min(values, Comparator.reverseOrder());
        Integer expected = 1;
        assertThat(result, is(expected));
    }
}
