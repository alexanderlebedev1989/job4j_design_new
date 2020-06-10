package ru.job4j.exam;

import java.util.Stack;

/**
 * Class Shell имитирует общие структуры каталогов
 * @autor Lebedev A.
 * @since 08.06.2020
 */
class Shell {

    private Stack<String> commands = new Stack<>();
    private String delimiter = "/";

    private final String NEXT = "[a-z]*";
    private final String BACK = "\\.\\.";
    private final String CATALOG = "//[a-z]*///";

    /**
     * метод, который создает путь к необходимому месту в файловой системе
     * @param path строка, которая является входным параметром для формирования строкового представления пути
     * @return возвращает объект класса Shell, который содержит стек вызванных команд.
     */
    Shell cd(final String path) {
        if (commands.empty() && delimiter.equals(path)) {
            commands.push(path);
        } else if (path.matches(NEXT)) {
            commands = goToNext(path);
        } else if (path.matches(BACK)) {
            commands.pop();
            commands.pop();
        } else if (path.matches(CATALOG)) {
            while (!commands.empty() && !commands.peek().equals(delimiter)) {
                commands.pop();
            }
            commands = goToFolder(path);
        }
        return this;
    }

    /**
     * метод, который реализует строковое представление пути
     * @return возвращает строку ввиде путя
     */
    public String path() {
        StringBuilder sb = new StringBuilder();
        if (commands.empty()) {
            return delimiter;
        } else {
            for (String str: commands) {
                sb.append(str);
            }
            return sb.toString();
        }
    }

    /**
     * метод работает, когда необходимо перейти в следующий каталог
     * @param path строка, которая является входным параметром для формирования строкового представления пути
     * @return возвращает стек с добавленной командой
     */
    public Stack<String> goToNext(String path) {
        if (commands.peek().equals(delimiter)) {
            commands.push(path);
        } else {
            commands.push(delimiter);
            commands.push(path);
        }
        return commands;
    }

    /**
     * метод работает, когда необходимо перейти в новый каталог
     * @param path строка, которая является входным параметром для формирования строкового представления пути
     * @return возвращает стек с добавленной командой
     */
    public Stack<String> goToFolder(String path) {
        String[] strings = path.split("/");
        for (String s : strings) {
            if (!s.equals("")) {
                commands.push(s);
            }
        }
        return commands;
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

