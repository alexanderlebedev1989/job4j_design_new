package ru.job4j_design_new.gc;

import java.util.ArrayList;
import java.util.List;

public class MemoryUsage {
    public static void main(String[] args) {
        System.out.println("start");
        List<User> users = new ArrayList<>();
        for (int i = 0; i <100000; i++) {
            users.add(new User("test"));
        }
        info();
        users = null;
        System.out.println("наверное сейчас придет сборщик");
        System.out.println();
        info();



    }

    public static void info() {

        int mb = 1024 * 1024;

        Runtime runtime = Runtime.getRuntime();

        System.out.println("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        System.out.println("Free memory: " + runtime.freeMemory() / mb);

        System.out.println("Total memory: " + runtime.totalMemory() / mb);

        System.out.println("Max memory " + runtime.maxMemory() / mb);
    }

    public static class User {
        public String name;

        public User(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}

