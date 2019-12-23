package com.aaryanprakash.app;

import java.sql.*;

public class App {
    private Connection conn;
    private String url;
    private String username;
    private String password;

    public App() {
        url = "jdbc:mysql://usercheck-db:3306/db?useSSL=false";
        username = "root";
        password = "pass";
    }

    public static void main(String[] args) {
        (new App()).run();
    }

    public void run() {
        loadDriver();

        try {
            printNames();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }

    public void loadDriver() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public void printNames() throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "SELECT id, name, age FROM people;";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");

            System.out.printf("Name: %s\nAge: %d\n\n", name, age);
        }

        rs.close();
        stmt.close();
        conn.close();
    }
}
