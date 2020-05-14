package ru.job4j.services;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {

            private Iterator<Integer> iterator = Collections.emptyIterator();

            @Override
            public boolean hasNext() {
                while (it.hasNext() && !iterator.hasNext()) {
                    iterator = it.next();
                }
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return iterator.next();
            }
        };
    }
}
