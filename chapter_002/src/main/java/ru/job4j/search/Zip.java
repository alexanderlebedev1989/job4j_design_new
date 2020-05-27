package ru.job4j.search;

import java.io.*;;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(target))) {
            for (File f : sources) {
                zos.putNextEntry(new ZipEntry(f.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(f))) {
                    zos.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<File> seekBy(ArgZip args) throws IOException {
        SearchFiles sf = new SearchFiles(path -> !path.toFile().getName().endsWith(args.exclude()));
        Path folder = Paths.get(args.directory());
        Files.walkFileTree(folder, sf);
        return sf.getFiles();
    }

    public static void main(String[] args) throws IOException {
        new Zip().packSingleFile(
                new File("./chapter_002/pom.xml"),
                new File("./chapter_002/pom.zip")
        );

        ArgZip argZip = new ArgZip(args);
        argZip.parse(args);

        Zip zip = new Zip();
        List<File> files = zip.seekBy(argZip);
        File folder = new File(argZip.output());
        zip.packFiles(files, folder);
    }
}
