package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    public  Node<E>[] container;

    transient Node<E> first;

    transient Node<E> last;

    transient int size;

    private int modCount;

    private int indexForIterator;

    public SimpleLinkedList() {
        this.container = new Node[10];
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<E>[] grow() {
    return Arrays.copyOf(container, container.length * 2);
    }


    @Override
    public void add(E value) {
        if (size == container.length) {
            container = grow();
        }
        Node<E> l = last;
        container[size] = new Node<>(l, value, null);
        last = container[size];
        if (first == null) {
            first = container[size];
        } else {
            l.next = container[size];
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return container[index].item;
    }

    @Override
    public Iterator<E> iterator() {
        final int expectedModCount = modCount;
        indexForIterator = 0;
        return new Iterator<E>() {

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return indexForIterator < size;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[indexForIterator++].item;
            }
        };
    }
}
