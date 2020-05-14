package ru.job4j.io;

import java.io.*;

public class Lesson1 {
    public static void main(String[] args) throws IOException {
         // чтение данных с клавиатуры
        System.out.println("Введите число");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(n);

        // запись в файл
        BufferedWriter bw = new BufferedWriter(new FileWriter("result.txt"));
        String str = "Hello Java";
        bw.write(str);
        bw.close();

        // чтение посимвольно
        BufferedReader buff = new BufferedReader(new FileReader("result.txt"));
        int c;
        while ((c = buff.read()) != -1) {
            System.out.println((char) c);
        }
        buff.close();

        // чтение построчно
        BufferedReader buff1 = new BufferedReader(new FileReader("result.txt"));
        String c1;
        while ((c1 = buff1.readLine()) != null) {
            System.out.println(c);
        }
        buff.close();

    }
}
