package ru.job4j.io.duplicates;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;


public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("."), duplicatesVisitor);
        DuplicatesFinder finder = new DuplicatesFinder();
        finder.duplicatesPrint(new File("DuplicatesVisitor.jar"), duplicatesVisitor.registry);

    }

    public void duplicatesPrint(File file, HashMap<FileProperty, List<Path>> paths) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file))) {
            for (FileProperty key : paths.keySet()) {
                if (paths.get(key).size() != 1) {
                    System.out.println(key.getName() + " - " + key.getSize() + "b");
                    out.println(key.getName() + " - " + key.getSize() + "b");
                    for (Path link : paths.get(key)) {
                        System.out.println(link.normalize().toFile().getAbsolutePath());
                        out.println(link.normalize().toFile().getAbsolutePath());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}