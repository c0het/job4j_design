package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    transient Node<E> first;

    transient Node<E> last;

    transient int size;

    private int modCount;

    private int indexForIterator;


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

    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newElem = new Node<>(l, value, null);
        last = newElem;
        if (first == null) {
            first = newElem;
        } else {
            l.next = newElem;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = first;
        for (int i = 0; i != index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
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
                return get(indexForIterator++);
            }
        };
    }
}
