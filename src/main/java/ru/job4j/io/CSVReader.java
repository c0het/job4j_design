package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        List<List<String>> lines = new ArrayList<>();
        List<Integer> index = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(argsName.get("path")));
             PrintWriter out = new PrintWriter(new FileOutputStream(argsName.get("out")))) {
            while (scanner.hasNext()) {
                List<String> line = new ArrayList<>(Arrays.asList(scanner.nextLine().split(argsName.get("delimiter"))));
                lines.add(line);
            }
            for (String line : lines.get(0)) {
                for (String filter : argsName.get("filter").split(",")) {
                    if (line.equals(filter)) {
                        index.add(lines.get(0).indexOf(line));
                    }
                }
            }
            for (List<String> line : lines) {
                    for (int i : index) {
                        if (index.indexOf(i) != index.size() - 1) {
                            out.print(line.get(i) + ";");
                    } else {
                            out.print(line.get(i) + System.lineSeparator());
                        }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}