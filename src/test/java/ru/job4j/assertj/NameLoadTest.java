package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyNames() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty");
    }

    @Test
    void checkContains() {
        NameLoad nameLoad = new NameLoad();
        String s = "key;value";
        assertThatThrownBy(() -> nameLoad.parse(s))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain");

    }

    @Test
    void checkStartWith() {
        NameLoad nameLoad = new NameLoad();
        String s = "key=";
        assertThatThrownBy(() -> nameLoad.parse(s))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageEndingWith("a value")
                .message()
                .isNotEmpty();

    }


}