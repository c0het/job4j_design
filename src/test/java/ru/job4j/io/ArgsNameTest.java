package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class ArgsNameTest {

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertThat(jvm.get("request"), is("?msg=Exit="));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        jvm.get("Xms");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongSomeArgument() {
        ArgsName jvm = ArgsName.of(new String[] {"-enconding=UTF-8", "-Xmx="});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgsIsNull() {
        ArgsName jvm = ArgsName.of(new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyIsNull() {
        ArgsName jvm = ArgsName.of(new String[]{"-=512"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongFormat() {
        ArgsName jvm = ArgsName.of(new String[]{"-xmx512"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWithoutHyphen() {
        ArgsName jvm = ArgsName.of(new String[]{"xmx=512"});
    }
}