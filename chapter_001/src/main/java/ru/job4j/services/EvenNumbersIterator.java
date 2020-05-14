package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] values;
    private int position;

    public EvenNumbersIterator(final int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return findEvenNumbers() == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return values[position++];
    }

    public Integer findEvenNumbers() {
        int number = -1;
        for (int index = position; index != values.length; index++) {
            if (values[index] % 2 == 0) {
                position = index;
                number = 0;
                break;
            }
        }
        return number;
    }
}
