package ru.job4j.chat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ConsoleChat {
    private List<String> sayWords = new ArrayList<>();
    private Random rand = new Random();
    private File file;
    private boolean finish = false;
    private boolean stop = false;
    private final String STOP = "стоп";
    private final String CONTINUE = "продолжить";
    private final String FINISH = "закончить";

    public ConsoleChat(File file) {
        this.file = file;
    }

    public void writeRecord() {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String str : sayWords)
                out.print(str + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void readRecord() {
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
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!finish) {
                System.out.println("Введите слово или фразу");
                String say = br.readLine();
                sayWords.add(say);
                if (say.equals(FINISH)) {
                    System.out.println();
                    System.out.println("===Текстовый лог===");
                    writeRecord();
                    readRecord();
                    System.out.println("===Программа завершена===");
                    finish = true;
                    stop = true;
                } else {
                    if (say.equals(STOP)) stop = true;
                    else if (say.equals(CONTINUE)) stop = false;
                }
                if (!stop) {
                    int index = rand.nextInt(sayWords.size());
                    System.out.println(sayWords.get(index));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
