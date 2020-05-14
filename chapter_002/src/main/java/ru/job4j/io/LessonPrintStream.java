package ru.job4j.io;

import java.io.*;

public class LessonPrintStream {
    public static void main(String[] args) throws FileNotFoundException {
        String str = "hhhello";
        PrintStream ps = new PrintStream(new FileOutputStream("result.txt"));
        ps.println(str);
        ps.close();
    }
}
