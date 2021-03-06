package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(s ->  {
                if (!s.startsWith("#") && !s.equals("")) {
                    String[] keysAndValues = s.split("=");
                    if (keysAndValues.length < 2 || keysAndValues[0].isEmpty()) {
                        throw new IllegalArgumentException("Не верно указан формат");
                    }
                    values.put(keysAndValues[0], s.substring(keysAndValues[0].length() + 1));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("./data/wrong_format(=value).properties");
        config.load();
    }

}