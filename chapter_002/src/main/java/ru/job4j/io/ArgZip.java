package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class ArgZip extends SimpleFileVisitor<Path> {
    private List<File> files = new ArrayList<>();

    public List<File> getFiles() {
        return files;
    }

   @Override
   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
       files.add(file.toFile());
       return CONTINUE;
   }
}
