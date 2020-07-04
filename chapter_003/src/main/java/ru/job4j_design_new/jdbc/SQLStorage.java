package ru.job4j_design_new.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SQLStorage {

    public static final Logger LOG = LoggerFactory.getLogger(SQLStorage.class);

//    public void test() {
//        String url = "jdbc:postgresql://localhost:5432/products_db";
//        String username = "postgres";
//        String password = "password";
//        try (Connection c = DriverManager.getConnection(url, username, password);
//             PreparedStatement st = c.prepareStatement("DELETE FROM products WHERE prod_id = ?"))
//              {
//                  st.setInt(1, 10);
//                  st.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/products_db";
        String username = "postgres";
        String password = "password";
        Connection conn = null;
        try {
            // с помощью объекта conn мы подключились к базе данных
            conn = DriverManager.getConnection(url, username, password);
//            PreparedStatement st = conn.prepareStatement("INSERT INTO products (prod_name, type_id, expired_date, price, count) " +
//                    "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//            PreparedStatement st = conn.prepareStatement("UPDATE products SET prod_name = ? WHERE prod_id = ?");
//            PreparedStatement st = conn.prepareStatement("DELETE FROM products WHERE prod_id = ?");
//            st.setInt(1, 10);
//            st.executeUpdate();
            PreparedStatement st = conn.prepareStatement("SELECT prod_name FROM products  WHERE prod_id IN (?)");
            st.setInt(1, 16);
            ResultSet rs = st.executeQuery();
            boolean result = rs.next();
            if (!result) {
                System.out.println("Давай до свидания");
            } else {
                String s = rs.getString("prod_name");
                System.out.println(s);
            } rs.close();
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }
}
