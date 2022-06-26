package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        Search.chekArgs(args);
        String s = args[0];
        Path start = Paths.get(args[0]).normalize().toAbsolutePath();
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void chekArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Не все параметры указаны");
        }
        if (!Paths.get(args[0]).toFile().exists()) {
            throw new IllegalArgumentException("Указаный каталог не существует");
        }
        if (!Paths.get(args[0]).toFile().isDirectory()) {
            throw new IllegalArgumentException("Указаный путь ведет не каталогу");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Расширение файла не верное");
        }
    }
}