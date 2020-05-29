package ru.job4j.chat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ConsoleChat {

    private List<String> answers = new ArrayList<>();
    private List<String> log = new ArrayList<>();

    private Random rand = new Random();

    private File logText;
    private File textFish;

    private boolean finish = false;
    private boolean stop = false;

    private final String STOP = "стоп";
    private final String CONTINUE = "продолжить";
    private final String FINISH = "закончить";

    public ConsoleChat(File logText, File textFish) {
        this.logText = logText;
        this.textFish = textFish;
    }

    public void writeToLog() {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(logText)))) {
            for (String str : log)
                out.print(str + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void readFishText() {
        try (BufferedReader br = new BufferedReader(new FileReader(textFish))) {
            br.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startRecord() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!finish) {
                System.out.println("Введите слово или фразу");
                String say = br.readLine();
                log.add(say);
                readFishText();
                if (say.equals(FINISH)) {
                    writeToLog();
                    System.out.println("===Программа завершена===");
                    finish = true;
                    stop = true;
                } else {
                    if (say.equals(STOP)) stop = true;
                    else if (say.equals(CONTINUE)) stop = false;
                }
                if (!stop) {
                    int index = rand.nextInt(answers.size());
                    log.add(answers.get(index));
                    System.out.println(answers.get(index));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
