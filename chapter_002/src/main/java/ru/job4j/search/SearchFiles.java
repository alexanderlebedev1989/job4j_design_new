package ru.job4j.search;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles extends SimpleFileVisitor<Path> {

    private Function<Path, Boolean> function;

    private final List<File> files = new ArrayList<>();

    public SearchFiles(Function<Path, Boolean> function) {
        this.function = function;
    }

    public List<File> getFiles() {
        return files;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean result = function.apply(file);
        if (result) files.add(file.toFile());
        return CONTINUE;
    }
}
