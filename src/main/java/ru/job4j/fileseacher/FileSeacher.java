package ru.job4j.fileseacher;


import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileSeacher {
    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        List<Path> files;
        files = createSource(argsName);
        try (Writer out = new FileWriter(argsName.get("o"))) {
            for (Path file : files) {
                out.write(file.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Path> createSource(ArgsName argsName) {
        List<Path> rsl = new ArrayList<>();

        try {
            if ("mask".equals(argsName.get("t"))) {
                PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*"
                        + argsName.get("n"));
                rsl = Search.search(Paths.get(argsName.get("d")), matcher::matches);
            } else if ("name".equals(argsName.get("t"))) {

                rsl = Search.search(Paths.get(argsName.get("d")),
                        f -> f.getFileName()
                        .toString()
                        .equals(argsName.get("n")));

            } else if ("regex".equals(argsName.get("t"))) {
                rsl = Search.search(Paths.get(argsName.get("d")), f -> f.toString().contains(argsName.get("n")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
