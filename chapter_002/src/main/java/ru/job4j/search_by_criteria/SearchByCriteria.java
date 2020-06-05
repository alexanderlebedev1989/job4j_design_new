package ru.job4j.search_by_criteria;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class SearchByCriteria {

    public List<File> find(ArgKeys keys) throws IOException {
        Search search = new Search(defineCriteria(keys));
        Path folder = Paths.get(keys.directory());
        Files.walkFileTree(folder, search);
        return search.getFiles();
    }

    public Predicate<Path> defineCriteria(ArgKeys keys) {
        Predicate<Path> paths = null;
        if (keys.getValues().containsKey(keys.getFULL())) {
            paths = path1 -> path1.toFile().getName().equals(keys.name());
        } else if (keys.getValues().containsKey(keys.getMASK())) {
            paths = path1 -> path1.toFile().getName().endsWith(keys.name());
        } else if (keys.getValues().containsKey(keys.getREGEX())) {
            paths = path1 -> path1.toFile().getName().matches(keys.name());
        }
        return paths;
    }

    public void write(List<File> list, ArgKeys keys) {
        try(PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(keys.output())))) {
            if (list.size() == 0) {
                out.write("File not found");
            } else {
                for (File f : list) {
                    out.print(f.getName() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        SearchByCriteria searchByCriteria = new SearchByCriteria();
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        List<File> files = searchByCriteria.find(argKeys);
        searchByCriteria.write(files, argKeys);
    }
}
