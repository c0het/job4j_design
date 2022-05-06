package ru.job4j.collection;

import ru.job4j.list.List;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        size++;
        container[size] = value;
        modCount++;

    }

    @Override
    public T set(int index, T newValue) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, container.length);
        T element = container[index];
        container[index] = newValue;
        modCount++;
        return element;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, container.length);
    }

    @Override
    public T get(int index) {

    }

    @Override
    public int size() {

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {

            }

            @Override
            public T next() {

            }

        };
    }
}