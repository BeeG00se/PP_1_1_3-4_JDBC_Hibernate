package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/myschema?useSSL=false";
    private static final String userName = "root";
    private static final String password = "root";

    public static Connection connect() {//todo: избавляемся от static, ..ломаем парадигму ООП
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, userName, password);
            System.out.println("We are connected");
            return connection;
        } catch (Exception e) {
            throw new RuntimeException("...." + e.getMessage());//todo: обритить внимание - ..дописать "....". Если работа приложения далее не целесообразна - мы его рогяем. Так
        }
    }
}
