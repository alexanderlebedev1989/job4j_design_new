package ru.job4j.io;

import java.io.FileOutputStream;

public class MatriceFile {

    public static void main(String[] args) {
        ArrayMatrice am = new ArrayMatrice();
        try (FileOutputStream out = new FileOutputStream("Matrice.txt")) {
            out.write(am.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
