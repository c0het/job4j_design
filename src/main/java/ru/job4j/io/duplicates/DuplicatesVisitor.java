package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {


    HashMap<FileProperty, List<Path>> registry = new HashMap<>();


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!file.startsWith("./.git")) {
            FileProperty fileProperty = new FileProperty(Files.size(file), file.getFileName().toString());
            if (registry.containsKey(fileProperty)) {
                registry.get(fileProperty).add(file);
            } else {
                List<Path> values = new ArrayList<>();
                values.add(file);
                registry.put(fileProperty, values);
            }
        }
        return super.visitFile(file, attrs);
    }
}

