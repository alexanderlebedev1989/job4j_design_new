package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles extends SimpleFileVisitor<Path> {

    private List<Path> paths = new ArrayList<>();

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        paths.add(file.getFileName());
        return CONTINUE;
    }
}
