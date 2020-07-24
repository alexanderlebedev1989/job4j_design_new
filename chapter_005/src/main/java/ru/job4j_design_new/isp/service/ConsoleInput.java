package ru.job4j_design_new.isp.service;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askName(String name) {
        System.out.print(name);
        return scanner.nextLine();
    }
}
