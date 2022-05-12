package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    transient Node<E> first;

    transient Node<E> last;

    transient int size;

    private int modCount;

    private Node<E> nodeForIter;

    private E elem;



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
        nodeForIter = first;
        elem = null;
        return new Iterator<E>() {

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return size != 0 && last.item != elem;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (nodeForIter != last) {
                    nodeForIter = nodeForIter.next;
                    elem = nodeForIter.prev.item;
                } else {
                    elem = nodeForIter.item;
                }
                return elem;
            }
        };
    }
}



