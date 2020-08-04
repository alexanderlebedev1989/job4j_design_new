package ru.job4j_design_new.tictactoe.input;

import java.util.Scanner;

public class InputConsole implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public int ask(String name) {
        System.out.print(name);
        return scanner.nextInt();
    }
}
