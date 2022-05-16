package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn = 0;
    private int sizeOut = 0;


    public T poll() throws NoSuchElementException {
        while (sizeIn != 0) {
            out.push(in.pop());
            sizeOut++;
            sizeIn--;
        }
        sizeOut--;
        return out.pop();

    }
    public void push(T value) {
        while (sizeOut != 0) {
            in.push(out.pop());
            sizeOut--;
            sizeIn++;
            }
        in.push(value);
        sizeIn++;
    }
}

