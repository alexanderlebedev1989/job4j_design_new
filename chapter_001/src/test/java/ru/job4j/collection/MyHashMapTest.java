package ru.job4j.collection;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MyHashMapTest {

    @Test
    public void insert() {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        myHashMap.insert("Li", "first");
        myHashMap.insert("Vladimir", "third");
        myHashMap.insert("Vladimir", "second");
        String result = myHashMap.get("Vladimir");
        assertThat(result, is("second"));
    }

    @Test
    public void get() {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        myHashMap.insert("Li", "first");
        String result = myHashMap.get("Li");
        assertThat(result, is("first"));
    }

    @Test
    public void delete() {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        myHashMap.insert(null, "first");
        assertThat(myHashMap.delete(null), is(true));

    }
    @Test
    public void iterator() {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        myHashMap.insert("Li", "first");
        myHashMap.insert("Vladimir", "third");
        Iterator<String> it = myHashMap.iterator();
        assertThat(it.next(), is("third"));
        assertThat(it.next(), is("first"));
    }
}
