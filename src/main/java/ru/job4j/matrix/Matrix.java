package ru.job4j.matrix;

import java.io.FileOutputStream;

public class Matrix {
    public static void main(String[] args) {
        int[][] rsl = new int[10][10];
        try (FileOutputStream out = new FileOutputStream("resultMatrix.txt")) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    rsl[i][j] = (i + 1) * (j + 1);
                    out.write(String.valueOf(rsl[i][j]).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
