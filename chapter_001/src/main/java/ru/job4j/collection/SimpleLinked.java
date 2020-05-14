package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinked<E> implements Iterable<E> {

    private Node<E> head;
    private int pos = -1;
    private int modCount = 0;

    public void add(E value) {
        modCount++;
        Node<E> node = new Node<>(value, null);
        if (head == null) {
            head = node;
        } else {
            Node<E> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
        pos++;
    }

    public E get(int index) {
        Node<E> temp = head;
        int currentIndex = 0;
        while (temp != null) {
            if (currentIndex == index) {
                return temp.value;
            }
            temp = temp.next;
            currentIndex++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E deleteLast() {
        int idx = 0;
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (idx == pos) {
            return deleteIfOneElement();
        }
        Node<E> temp = head;
        while (idx != pos - 1) {
            temp = temp.next;
            idx++;
        }
        E value = temp.next.value;
        temp.next = null;
        pos--;
        return value;
    }

    public E deleteIfOneElement() {
        E value = head.value;
        head = head.next;
        pos--;
        return value;
    }

    public boolean emptyStack() {
        return head == null;
    }

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            Node<E> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = node.value;
                node = node.next;
                return value;
            }
        };
    }
}