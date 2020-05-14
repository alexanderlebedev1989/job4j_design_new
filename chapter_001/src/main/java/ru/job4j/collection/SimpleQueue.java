package ru.job4j.collection;

import org.w3c.dom.Node;

import java.util.Stack;

public class SimpleQueue<T>  {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();


    public T poll() {
        while (!in.isEmpty()) {
            T value = in.pop();
            out.push(value);
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }

}
