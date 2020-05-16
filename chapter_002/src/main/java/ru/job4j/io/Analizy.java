package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

    private List<String> listTimes = new ArrayList<>();
    private String startTimes;
    private String endTimes;

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            List<String> strings = new ArrayList<>();
            read.lines().filter(s -> !s.isEmpty()).forEach(strings::add);
            boolean available = true;
            for (String s : strings) {
                if (available && s.charAt(0) == '4' || s.charAt(0) == '5') {
                    startTimes = s.substring(4);
                    available = false;
                } else if (!available && s.charAt(0) == '2') {
                    endTimes = s.substring(4);
                    available = true;
                    listTimes.add(String.join(";", startTimes, endTimes));
                }
            }
            writeFile(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void writeFile(String target) {
        try (PrintWriter pr = new PrintWriter(new FileOutputStream(target))) {
            for (String s : listTimes) {
                String[] strings = s.split(";");
                pr.printf("Начало периода: %s, конец периода: %s" + System.lineSeparator(), strings[0], strings[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
