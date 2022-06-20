package ru.job4j.io;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Test
    public void test() throws IOException {
        folder.create();
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("""
                    200 10:56:01
                    500 10:57:01
                    400 10:58:01
                    200 10:59:01
                    300 10:59:55
                    500 11:01:02
                    200 11:02:02""");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is(" 10:57:01 - 10:59:01 11:01:02 - 11:02:02"));
    }
}