package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final String USER_NAME = "root";//todo: - правильное именование констант (которые выносим из тела метода)
    String password = "my179sql";
    String connectionURL = "jdbc:mysql://localhost:3306/myschema";

    public static Connection connect() throws ClassNotFoundException {//todo: избавляемся от static, так мы ломаем ООП парадигму
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);
            System.out.println("We are connected");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("........" + e.getMessage());//todo: грамотно - так
        }
    }
}
