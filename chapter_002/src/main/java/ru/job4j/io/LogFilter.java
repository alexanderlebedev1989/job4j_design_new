package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {

    public static List<String> filter(String file)  {
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().filter(s -> s.contains("404")).
                    filter(s -> !s.endsWith("-")).forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(file)))) {

            for (String line : log) {
                out.write(line + System.lineSeparator());
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
