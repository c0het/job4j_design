package ru.job4j.io.duplicates;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;


public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("."), duplicatesVisitor);
        try (PrintWriter out = new PrintWriter(new FileOutputStream("DuplicatesVisitor.jar"))) {
            for (FileProperty key : duplicatesVisitor.registry.keySet()) {
                if (duplicatesVisitor.registry.get(key).size() != 1) {
                    out.println(key);
                    out.println(duplicatesVisitor.registry.get(key));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}