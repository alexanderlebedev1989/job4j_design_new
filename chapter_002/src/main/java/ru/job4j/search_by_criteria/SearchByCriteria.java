package ru.job4j.search_by_criteria;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class SearchByCriteria {

    public static void main(String[] args) throws IOException {

        SearchByCriteria searchByCriteria = new SearchByCriteria();
        ArgKeys argKeys = new ArgKeys(args);
        argKeys.parse(args);
        List<File> files = searchByCriteria.find(argKeys);
        searchByCriteria.write(files, argKeys);
    }

    public Predicate<Path> defineCriteria(ArgKeys keys) {
        Predicate<Path> paths = null;
        if (keys.getValues().containsKey(keys.getFULL())) {
            paths = path1 -> path1.toFile().getName().equals(keys.name());
        } else if (keys.getValues().containsKey(keys.getMASK())) {
            String pattern = preparePattern(keys.name());
            paths = path1 -> path1.toFile().getName().matches(pattern);
        } else if (keys.getValues().containsKey(keys.getREGEX())) {
            paths = path1 -> path1.toFile().getName().matches(keys.name());
        }
        return paths;
    }

    public List<File> find(ArgKeys keys) throws IOException {
        Search search = new Search(defineCriteria(keys));
        Path folder = Paths.get(keys.directory());
        Files.walkFileTree(folder, search);
        return search.getFiles();
    }

    public static String preparePattern(String pattern) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == '*') {
                sb.append(".*");
            } else if (c == '.') {
                sb.append("\\.");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
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


}
