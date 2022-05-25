package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private int indexForIterator = 0;

    private int foundElemInIterator = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        expand();
        int i = indexFor(key);
        boolean rsl = (table[i]) == null;
        if (rsl) {
            table[i] = new MapEntry<>(key, value);
            modCount++;
            count++;
        }
        return rsl;
    }


    private int hash(K key) {
        return key.hashCode() ^ (key.hashCode() >>> 16);
    }

    private int indexFor(K key) {
        return (table.length - 1) & hash(key);
    }

    private void expand() {
        if (count / capacity >= LOAD_FACTOR) {
            capacity *= 2;
            MapEntry<K, V>[] tableForCopy = new MapEntry[capacity];
            for (MapEntry<K, V> mapEntry : table) {
                tableForCopy[indexFor(mapEntry.key)] = mapEntry;
            }
            table = tableForCopy;
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        int i = indexFor(key);
        if (table[i] != null) {
            value = table[i].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        int i = indexFor(key);
        boolean rsl = table[i] != null;
        if (rsl) {
            table[i] = null;
            modCount++;
            count--;
        }
        return rsl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleMap)) {
            return false;
        }
        SimpleMap<?, ?> simpleMap = (SimpleMap<?, ?>) o;
        return capacity == simpleMap.capacity && count == simpleMap.count && modCount == simpleMap.modCount && Arrays.equals(table, simpleMap.table);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity, count, modCount);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }


    @Override
    public Iterator<K> iterator() {
        final int expectedModCount = modCount;
        return new Iterator<>() {

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[indexForIterator] == null && foundElemInIterator < count) {
                    indexForIterator++;
                }
                return foundElemInIterator < count;
            }

            @Override
            public K next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                foundElemInIterator++;
                return table[indexForIterator++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}