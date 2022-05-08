package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    private int indexForIterator;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size++] = value;
        modCount++;

    }

    @Override
    public T set(int index, T newValue) throws IndexOutOfBoundsException {
        T element = container[Objects.checkIndex(index, container.length)];
        container[index] = newValue;
        modCount++;
        return element;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        T rsl = container[Objects.checkIndex(index, container.length)];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        modCount++;
        size--;
        return rsl;
    }

    @Override
    public T get(int index) {
        return container[Objects.checkIndex(index, container.length)];

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        final int expectedModCount = modCount;
        indexForIterator = 0;
        return new Iterator<T>() {

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return indexForIterator < container.length && size != 0;
            }

            @Override
            public T next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[indexForIterator++];
            }
        };
    }
}