package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class Search {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT Folder.");
        }
        Path start = Paths.get(args[0]);
        search(start, args[1]).forEach(System.out::println);
    }
    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles();
        Files.walkFileTree(root, searcher);
        return searcher.getPaths().stream().
                filter(path -> path.toFile().getName().endsWith(ext)).collect(Collectors.toList());
    }
}

