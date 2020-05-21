package ru.job4j.io;


import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles extends SimpleFileVisitor<Path> {
    private Function<Path, Boolean> function;
    private Boolean result = true;
    private List<Path> paths = new ArrayList<>();

    public SearchFiles(Function<Path, Boolean> function) {
        this.function = function;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        result = function.apply(file);
        if (result) {
            paths.add(file.getFileName());
        }
        return CONTINUE;
    }
}
