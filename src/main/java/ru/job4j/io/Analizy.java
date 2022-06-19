package ru.job4j.io;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analizy {
    public void unavailable(String source, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target));
             BufferedReader readBuf = new BufferedReader(new FileReader(source))) {
            AtomicBoolean flag = new AtomicBoolean(true);
            readBuf.lines().forEach(e -> {
                if (flag.get() && (e.startsWith("400") || e.startsWith("500"))) {
                    flag.set(false);
                    out.print(e.substring(3));
                } else if (!flag.get() && (e.startsWith("200") || e.startsWith("300"))) {
                    out.println(" -" + e.substring(3));
                    flag.set(true);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("source", "unavailable.csv");
    }
}
