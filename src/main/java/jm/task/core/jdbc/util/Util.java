package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/myschema?useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    public Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("We are connected");
            return connection;
        } catch (Exception e) {
            throw new RuntimeException("Не удалось подключиться к БД" + e.getMessage());
        }
    }
}
