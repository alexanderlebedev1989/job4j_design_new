package ru.job4j.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Random;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {

        List<String> answers = List.of("How are you?", "What", "No money", "I am from Russia", "How much?");
        Random rand = new Random();
        boolean connection = true;

        try (ServerSocket server = new ServerSocket(9000)) {
            while (connection) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.contains("Hello")) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write("Hello, dear friend.".getBytes());
                        }
                        if (str.contains("Exit")) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            System.out.println("===Работа сервера завершена===");
                            connection = false;
                        }
                        if (str.contains("Any")) {
                            int index = rand.nextInt(answers.size());
                            String answer = answers.get(index);
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write(answer.getBytes());
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }
}
