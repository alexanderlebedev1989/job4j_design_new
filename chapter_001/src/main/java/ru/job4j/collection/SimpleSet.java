package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> simpleArray = new SimpleArray<>();

    public void add(E model) {
        if (!simpleArray.contains(model)) {
            simpleArray.add(model);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                return simpleArray.iterator().hasNext();
            }

            @Override
            public E next() {
                return simpleArray.iterator().next();
            }
        };
    }
}

