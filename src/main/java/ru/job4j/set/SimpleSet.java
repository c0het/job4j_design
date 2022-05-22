package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public String toString() {
        return "SimpleSet{"
                + "set=" + set
                +  '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleSet)) {
            return false;
        }
        SimpleSet<?> simpleSet = (SimpleSet<?>) o;
        return set.equals(simpleSet.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(set);
    }

    @Override
    public boolean add(T value) {
        boolean rsl = set.size() == 0 || !contains(value);
        if (rsl) {
            set.add(value);
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T s: set) {
            if (s == null && value == null || value.equals(s)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override

    public Iterator<T> iterator() {
        return set.iterator();
        }
}
