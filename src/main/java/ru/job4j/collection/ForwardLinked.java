package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;


    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public boolean revert() {
        boolean rsl = head != null && head.next != null;
        if (rsl) {
            Node<T> node = head.next;
            Node<T> node1 = head;
            head.next = null;
            while (node != null) {
                node1.next = head;
                head = node;
                node = node.next;
            }
            head.next = node1.next;
        }
        return rsl;
    }

    public T deleteFirst() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T delElem = head.value;
        Node<T> oldHead = head;
        head = head.next;
        oldHead.value = null;
        oldHead.next = null;
        return delElem;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}