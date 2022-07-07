package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {



    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zos.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                zos.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Path> createSource(Path directory, String exclude) {
        List<Path> source = new ArrayList<>();
        try {
                source = Search.search(directory, e -> !e.toFile().getName().endsWith(exclude));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return source;
    }

    private static void checkArgs(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("not all parameters are specified");
        }
        if (!Paths.get(args[0].substring(3)).toFile().exists()) {
            throw new IllegalArgumentException("directory is incorrect");
        }
        if (args[1].substring(3).length() == 0) {
            throw new IllegalArgumentException("no exception specified");
        }
        if (args[2].substring(3).length() == 0) {
            throw new IllegalArgumentException("archive is not specified");
        }
    }

    public static void main(String[] args) {
        checkArgs(args);
        ArgsName argsName = ArgsName.of(args);
        Zip zip = new Zip();
        zip.packFiles(createSource(Path.of(argsName.get("d")), argsName.get("e")), new File(argsName.get("o")));
    }
}