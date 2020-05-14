package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedListTest {
    @Test
    public void whenAddThenGet() {
        SimpleLinked<String> array = new SimpleLinked<>();
        array.add("first");
        array.add("second");
        array.add("third");
        String rsl = array.get(2);
        assertThat(rsl, is("third"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinked<String> array = new SimpleLinked<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleLinked<String> array = new SimpleLinked<>();
        array.get(0);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleLinked<String> array = new SimpleLinked<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinked<String> array = new SimpleLinked<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleLinked<String> array = new SimpleLinked<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("third");
        it.next();

    }
}
