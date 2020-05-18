package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Dir {
    public static void main(String[] args) throws IOException {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %ss", file.getAbsoluteFile()));
        }
        for (File subFile : file.listFiles()) {
            Path folder = Paths.get(subFile.getPath());
            System.out.println("file name: " + subFile.getName() +", "
                    + "file size: " + Files.walk(folder).filter(path -> path.toFile().isFile())
                    .mapToLong(path -> path.toFile().length()).sum());
        }
    }
}
