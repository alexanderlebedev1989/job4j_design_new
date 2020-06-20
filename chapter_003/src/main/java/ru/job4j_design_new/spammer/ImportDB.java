package ru.job4j_design_new.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB implements AutoCloseable {

    private String dump;
    private Connection cn;

    public ImportDB(String dump) {
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().map(s -> s.split(";")).forEach(strings -> users.add(new User(strings[0], strings[1])));
        }
        return users;
    }

    public void init() {
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties cfg = new Properties();
            cfg.load(in);
            Class.forName(cfg.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public void save(List<User> users) {
        for (User user : users) {
            try (PreparedStatement ps = cn.prepareStatement("INSERT INTO info_users (name, email) " +
                    "VALUES (?, ?) "))
            {
                ps.setString(1, user.name);
                ps.setString(2, user.email);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args)  {
        try (ImportDB db = new ImportDB("./chapter_003/src/main/resources/dump.txt"))
        {
            db.init();
            db.save(db.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

