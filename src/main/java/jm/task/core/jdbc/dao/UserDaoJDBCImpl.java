package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection;

    //todo: константы в java обозначаются так (строчные..):
    private static final String CREATE_USERS_QUERY  = "CREATE TABLE IF NOT EXISTS  USER (" +
            "Id int, Name varchar(256), Lastname varchar(256), Age int" +
            ")";
    private static final String dropUsersQuery = "DROP TABLE IF EXISTS USER";
    private static final String saveUserQuery = "INSERT INTO USER (name, lastName, age) VALUES (?, ?, ?)";
    private static final String removeUserQuery = "DELETE FROM USER WHERE id = ?";
    private static final String getAllUsersQuery = "SELECT * FROM USER";
    private static final String cleanUsersQuery = "TRUNCATE TABLE USER";


    public UserDaoJDBCImpl() {
//        Util util = new Util();//todo: codeStyle
        connection = new Util().connect();
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_USERS_QUERY);
        } catch (Exception e) {
            throw new RuntimeException("...." + e.getMessage());
        }
    }

    public void dropUsersTable() throws SQLException {//todo: throws SQLException.. - зачем?
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropUsersQuery);
        } catch (Exception e) {
            throw new RuntimeException("...." + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {//todo: try_with_resources - во всех методах
            PreparedStatement sql = connection.prepareStatement(saveUserQuery);
            sql.setString(1, name);
            sql.setString(2, lastName);
            sql.setByte(3, age);
            sql.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("...." + e.getMessage());//todo: .."...." - это что? Ронять приложение, только если не выполнится saveUser - я бы не стал. Просто можно ограничиться stackTrace()
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement sql = connection.prepareStatement(removeUserQuery);
            sql.setLong(1, id);
            sql.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("...." + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement sql = connection.createStatement()) {
            sql.execute(getAllUsersQuery);
            ResultSet re = sql.getResultSet();
            while (re.next()) {
                String name = re.getString("name");
                String lastName = re.getString("lastName");
                byte age = re.getByte("age");
                User user = new User(name, lastName, age);
                users.add(user);
            }
        } catch (Exception e) {
            throw new RuntimeException("...." + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(cleanUsersQuery);
        } catch (Exception e) {
            throw new RuntimeException("...." + e.getMessage());
        }
    }
}
