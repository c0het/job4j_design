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

    private T[] grow() {
        return container.length == 0 ? Arrays.copyOf(container, 10)
                : Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            container = grow();
        }
        container[size++] = value;
        modCount++;

    }

    @Override
    public T set(int index, T newValue) {
        T element = get(index);
        container[index] = newValue;
        modCount++;
        return element;
    }

    @Override
    public T remove(int index) {
        T rsl = get(index);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        modCount++;
        size--;
        return rsl;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];

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
                return indexForIterator < size && size != 0;
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