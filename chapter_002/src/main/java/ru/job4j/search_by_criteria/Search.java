package ru.job4j.search_by_criteria;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Search extends SimpleFileVisitor<Path> {

    private final List<File> files = new ArrayList<>();

    private Predicate<Path> paths;

    public List<File> getFiles() {
        return files;
    }

    public Search(Predicate<Path> paths) {
        this.paths = paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean result = paths.test(file);
        if (result) {
           files.add(file.toFile());
        }
        return CONTINUE;
    }

}
