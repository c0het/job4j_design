package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.System.out;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        List<List<String>> lines = new ArrayList<>();
        List<Integer> index = new ArrayList<>();
            try (Scanner scanner = new Scanner(new File(argsName.get("path")));
                 PrintWriter out =
                         ("stdout".equals(argsName.get("out"))
                         ? new PrintWriter(System.out)
                         : new PrintWriter(new FileOutputStream(argsName.get("out"))))) {
                while (scanner.hasNext()) {
                    List<String> line = new ArrayList<>(Arrays.asList(scanner.nextLine()
                            .split(argsName.get("delimiter"))));
                    lines.add(line);
                }
                for (String line : lines.get(0)) {
                    for (String filter : argsName.get("filter").split(",")) {
                        if (filter.equals(line)) {
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
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    private static void checkArgs(String[] args, ArgsName argsName) {
        if (args.length < 4) {
            throw new IllegalArgumentException("not all parameters entered");
        }
        if (!Paths.get(argsName.get("path")).toFile().exists()) {
            throw new IllegalArgumentException("file is not exist");
        }
        if (!";".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException("delimiter is not indicated");
        }
        if (argsName.get("filter").contains("name, age, last_name, education")) {
            throw new IllegalArgumentException("filter is empty");
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        checkArgs(args, argsName);
        handle(argsName);
    }
}