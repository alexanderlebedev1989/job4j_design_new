package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class LessonFileInputStream {
    public static void main(String[] args) throws IOException {
       FileInputStream fis = new FileInputStream("result.txt");
       int i;
       while ((i = fis.read()) != -1) {
           System.out.print((char) i);
       }
       fis.close();
    }
}
