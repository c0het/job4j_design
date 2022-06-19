package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;

public class Analizy {
    public void unavailable(String source, String target) {
        StringJoiner in = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(in::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
        boolean flag400or500 = false;
        boolean flag200or300 = false;
        for (String read : in.toString().split("\r\n")) {
            if (flag400or500 && Integer.parseInt(read.split(" ")[0]) == 400
                    || Integer.parseInt(read.split(" ")[0]) == 500) {
                flag200or300 = true;
                out.print(read.substring(3));
            } else if (flag200or300 && Integer.parseInt(read.split(" ")[0]) == 200
                        || Integer.parseInt(read.split(" ")[0]) == 300) {
                flag400or500 = true;
                out.println(" -" + read.substring(3));
                    }
                }
            } catch (IOException e) {
            e.printStackTrace();
        }
        }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("source", "unavailable.csv");
    }
}
