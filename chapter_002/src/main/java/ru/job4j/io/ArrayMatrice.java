package ru.job4j.io;

import java.util.StringJoiner;

public class ArrayMatrice {
    public static void main(String[] args) {
        int[][] array = new int[11][11];
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
               array[i][j] = i * j;
            }
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        stringJoiner.add("1 2 3 4 5 6 7 8 9 10");
        stringJoiner.add("2 4 6 8 10 12 14 16 18 20");
        stringJoiner.add("3 6 9 12 15 18 21 24 27 30");
        stringJoiner.add("4 8 12 16 20 24 28 32 36 40");
        stringJoiner.add("5 10 15 20 25 30 35 40 45 50");
        stringJoiner.add("6 12 18 24 30 36 42 48 54 60");
        stringJoiner.add("8 16 24 32 40 48 56 64 72 80");
        stringJoiner.add("9 18 27 36 45 54 63 72 81 90");
        stringJoiner.add("10 20 30 40 50 60 70 80 90 100");
        return stringJoiner.toString();
    }
}
