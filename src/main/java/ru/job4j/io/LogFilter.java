package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while (in.read() != -1) {
                rsl.add(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        rsl.removeIf(list -> !list.contains(" 404 "));
        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        for (String s: log) {
            System.out.println(s);
        }


    }
}