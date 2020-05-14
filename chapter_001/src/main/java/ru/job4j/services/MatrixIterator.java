package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] values;
    private int row;
    private int column;

    public MatrixIterator(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return row < values.length;
    }

    @Override
    public Integer next() {
        if (row >= values.length) {
            throw new NoSuchElementException();
        }
        var el = values[row][column];
        column++;
        while (row < values.length && column >= values[row].length) {
            column = 0;
            row++;
        }
        return el;
    }
}
