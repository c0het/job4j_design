package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotNull()
                .contains("ub")
                .isEqualTo("Cube");
    }

    @Test
    void isThisEight() {
        Box box = new Box(8, 5);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isGreaterThan(4)
                .isPositive()
                .isLessThan(10)
                .isNotZero()
                .isEqualTo(8);
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(10, 5);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isGreaterThan(-4)
                .isNegative()
                .isLessThan(10)
                .isNotZero()
                .isEqualTo(-1);
    }

    @Test
    void isThisTrue() {
        Box box = new Box(8, 5);
        boolean exist = box.isExist();
        assertThat(exist).isTrue()
                .isNotNull();
    }

    @Test
    void isThisFalse() {
        Box box = new Box(10, 5);
        boolean exist = box.isExist();
        assertThat(exist).isFalse()
                .isNotNull();
    }

    @Test
    void getAreaTestIfZeroVertex() {
        Box box = new Box(0, 4);
        double rsl = box.getArea();
        assertThat(rsl).isCloseTo(201.05d, withPrecision(0.02d))
                .isCloseTo(201.8d, Percentage.withPercentage(1.0d))
                .isGreaterThan(200.5d)
        ;
    }

    @Test
    void getAreaTestIfFourVertex() {
        Box box = new Box(4, 4);
        double rsl = box.getArea();
        assertThat(rsl).isCloseTo(27.7d, withPrecision(0.02d))
                .isCloseTo(27.8d, Percentage.withPercentage(1.0d))
                .isLessThan(200.5d)
        ;
    }


}