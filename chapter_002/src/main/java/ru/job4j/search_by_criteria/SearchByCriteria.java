package ru.job4j.search_by_criteria;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class SearchByCriteria {

    public List<File> find(ArgKeys keys) throws IOException {
        Search search = new Search(path -> path.toFile().getName().matches(keys.maskName()));
        Path folder = Paths.get(keys.directory());
        Files.walkFileTree(folder, search);
        return search.getFiles();
    }

    public void write(List<File> list, ArgKeys keys) {
        try(PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(keys.output())))) {
            if (list.size() == 0) {
                out.write("File not found");
            } else {
                for (File f : list) {
                    out.print(f.getAbsolutePath() + System.lineSeparator());
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
