package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private int index = 0;
    private int modCount = 0;
    private int size = 0;
    private int position = 0;

    private Object[] array = new Object[size];

    public T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    public void add(T model) {
        modCount++;
        if (index >= size) {
            array = Arrays.copyOf(array, size + 1);
            size++;
        }
        array[index++] = model;
    }

    public boolean contains(T model) {
        for (Object object : array) {
            if (object.equals(model)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return position != size;
            }

            @Override
            public T next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[position++];
            }
        };
    }
}
