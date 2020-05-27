package ru.job4j.chat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ConsoleChat {
    private List<String> sayWords = new ArrayList<>();
    private Random rand = new Random();
    private File file;
    private boolean exit = false;
    private boolean stop = false;

    public ConsoleChat(File file) {
        this.file = file;
    }

    public void writeRecord(String word, File file) {
        try (FileOutputStream out = new FileOutputStream(file, true)) {
            out.write((word + System.lineSeparator()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void randomAnswer(String word) {
        sayWords.add(word);
        int index = rand.nextInt(sayWords.size());
        System.out.println(sayWords.get(index));
    }

    public void readRecord(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startRecord() {
        Random rand = new Random();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!exit) {
                System.out.println("Введите слово или фразу");
                String say = br.readLine();
                writeRecord(say, file);
                if (say.equals("закончить")) {
                    System.out.println();
                    System.out.println("===Текстовый лог===");
                    readRecord(file);
                    System.out.println("===Программа завершена===");
                    exit = true;
                    stop = true;
                } else {
                    if (say.equals("стоп")) stop = true;
                    else if (say.equals("продолжить")) stop = false;
                }
                if (!stop)
                    randomAnswer(say);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
