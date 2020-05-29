package ru.job4j.chat;

import java.io.File;

public class Starter {
    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat(new File("Dialogue.txt"), new File("FishText.txt"));
        chat.startRecord();
    }
}