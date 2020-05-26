package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class ArgZip extends SimpleFileVisitor<Path> {

    private final String[] args;
    private final List<File> files = new ArrayList<>();
    private final Map<String, String> values = new HashMap<>();

    public ArgZip(String[] args) {
        this.args = args;
    }

    public String directory() {
        return values.get("d");
    }

    public String exclude() {
        return values.get("e");
    }

    public String output() {
        return values.get("o");
    }

    public List<File> getFiles() {
        return files;
    }

    public void parse(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException();
        }
        Arrays.stream(args)
                .map(s -> s.split("="))
                .forEach(strings -> values.put(strings[0].substring(1), strings[1]));
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String endOfWord = exclude();
        if (!file.toString().endsWith(endOfWord)) files.add(file.toFile());
        return CONTINUE;
    }
}


