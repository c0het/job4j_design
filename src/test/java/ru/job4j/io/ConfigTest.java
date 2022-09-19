package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void testWithApp() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.url"),
                is("jdbc:postgresql://localhost:5432/idea_db"));
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

    @Test
    public void whenOnlyComments() {
        String path = "./data/only_comments.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value(null));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongFormatOnlyKey() {
        String path = "./data/wrong_format(key=).properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongFormatOnlyValue() {
        String path = "./data/wrong_format(=value).properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenSomeSplitElem() {
        String path = "./data/some_split_elem.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect=PostgreSQL=Dialect"));
    }
}


