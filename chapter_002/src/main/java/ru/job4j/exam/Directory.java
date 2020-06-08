package ru.job4j.exam;

/**
 * Class Shell имитирует общие структуры каталогов
 * @autor Lebedev A.
 * @since 08.06.2020
 */
class Shell {

    private String newPath;
    private String delimiter = "/";
    private final String NEXT = "[a-z]*";
    private final String BACK = "\\.\\.";
    private final String CATALOG = "//[a-z]*///";

    /**
     * метод, который создает путь к необходимому месту в файловой системе
     * @param path строка, которая является входным параметром для формирования строкового представления пути
     * @return возвращает объект класса Shell, который содержит обновленный путь.
     */
    Shell cd(final String path) {
        if (newPath == null) {
            newPath = path;
        } else if (path.matches(NEXT)) {
            newPath = goToNext(path);
        } else if (path.matches(BACK)) {
            newPath = goBack();
        } else if (path.matches(CATALOG)) {
            newPath = goToFolder(path);
        }
        return this;
    }

    /**
     * метод, который реализует строковое представление пути
     * @return возвращает строку ввиде путя
     */
    public String path() {
        if (newPath == null) {
            return "/";
        } else {
            return newPath;
        }
    }

    /**
     * метод работает, когда необходимо перейти в следующий каталог
     * @param path строка, которая является входным параметром для формирования строкового представления пути
     * @return возвращает строку ввиде путя
     */
    public String goToNext(String path) {
        if (newPath.equals(delimiter)) {
            newPath += path;
        } else {
            newPath += delimiter + path;
        }
        return newPath;
    }

    /**
     * метод работает, когда необходимо вернуться в предыдущий каталог.
     * @return возвращает строку ввиде путя
     */
    public String goBack() {
        StringBuilder sb = new StringBuilder();
        String[] strings = newPath.split("/");
        for (int i = 0; i < strings.length - 1; i++) {
            if (!strings[i].equals("")) {
                sb.append(delimiter).append(strings[i]);
            }
        }
        return sb.toString();
    }

    /**
     * метод работает, когда необходимо перейти в новый каталог
     * @param path строка, которая является входным параметром для формирования строкового представления пути
     * @return возвращает строку путя с названием каталога, куда перешел пользователь
     */
    public String goToFolder(String path) {
        String[] strings = path.split("/");
        for (String s : strings) {
            if (!s.equals("")) {
                newPath = delimiter + s;
            }
        }
        return newPath;
    }
}

public class Directory {

    public static void main(String[] args) {

        final Shell shell = new Shell();
        assert shell.path().equals("/");

        shell.cd("/");
        assert shell.path().equals("/");

        shell.cd("usr/..");
        assert shell.path().equals("/");

        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        assert shell.path().equals("/usr/local");

        shell.cd("..");
        assert shell.path().equals("/usr");

        shell.cd("//lib///");
        assert shell.path().equals("/lib");
    }
}

