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
                    System.out.println(key.getName() + " - " + key.getSize() + "b");
                    out.println(key.getName() + " - " + key.getSize() + "b");
                    for (Path paths: duplicatesVisitor.registry.get(key)) {
                        System.out.println(paths.normalize().toFile().getAbsolutePath());
                        out.println(paths.normalize().toFile().getAbsolutePath());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}