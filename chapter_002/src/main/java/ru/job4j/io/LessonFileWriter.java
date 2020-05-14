package ru.job4j.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LessonFileWriter {
    public static void main(String[] args) throws IOException {
//        FileWriter fw = new FileWriter("result.txt", true);
//        String text = "I like Java";
//        fw.write(text);
//        fw.append("\n");
//        fw.close();

        FileReader fr = new FileReader("result.txt");
        int c;
        while ((c = fr.read()) != -1) {
            System.out.print((char) c);
        }
        fr.close();
    }
}
