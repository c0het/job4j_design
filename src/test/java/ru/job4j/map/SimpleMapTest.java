package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;


import static org.junit.jupiter.api.Assertions.*;

public class SimpleMapTest {


    @Test
    public void whenPutTrue() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, "Ivan"));
        assertTrue(simpleMap.put(2, "Dmitrii"));
    }

    @Test
    public void whenPutFalse() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "Ivan");
        assertFalse(simpleMap.put(1, "Ivan"));
    }

    @Test
    public void whenPutKeyCloneButDiffValue() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "Ivan");
        simpleMap.put(1, "Dmitrii");
        assertFalse(simpleMap.put(1, "Ivan"));
    }

    @Test
    public void getWhenMapHasElem() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "Ivan");
        assertEquals("Ivan", simpleMap.get(1));
    }

    @Test
    public void getWhenMapHasNotElem() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "Ivan");
        assertNull(simpleMap.get(2));
    }

    @Test
    public void whenRemoveSecondAndTryGetIt() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "Ivan");
        simpleMap.put(2, "Dmitrii");
        assertTrue(simpleMap.remove(2));
        assertNull(simpleMap.get(2));
        assertEquals("Ivan", simpleMap.get(1));
    }


    @Test
    public void whenRemoveElem() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(2, "Dmitrii");
        assertTrue(simpleMap.remove(2));
        assertNull(simpleMap.get(2));
    }

    @Test
    public void whenRemoveNullElem() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertFalse(simpleMap.remove(1));
    }


    @Test
    public void testIterator() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "Ivan");
        simpleMap.put(2, "Nick");
        simpleMap.put(3, "Dmitrii");
        simpleMap.put(4, "Anatolii");
        Iterator<Integer> iterator = simpleMap.iterator();
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
    }

    @Test (expected = NoSuchElementException.class)
    public void testIteratorNoSuchElem() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "Ivan");
        simpleMap.put(2, "Nick");
        Iterator<Integer> iterator = simpleMap.iterator();
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
    }

    @Test
    public void testExpand() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "Ivan1");
        simpleMap.put(2, "Ivan2");
        simpleMap.put(3, "Ivan3");
        simpleMap.put(4, "Ivan4");
        simpleMap.put(5, "Ivan5");
        simpleMap.put(6, "Ivan6");
        simpleMap.put(7, "Ivan7");
        simpleMap.put(8, "Ivan8");
        simpleMap.put(9, "Ivan9");
        assertEquals("Ivan9", simpleMap.get(9));
    }


}