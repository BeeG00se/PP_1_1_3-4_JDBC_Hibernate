package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDaoJDBC = new UserDaoJDBCImpl();//todo: инициализация - через constructor

    public void createUsersTable() throws SQLException {
        userDaoJDBC.createUsersTable();
        System.out.println("Таблица создана");
    }

    public void dropUsersTable() throws SQLException {
        userDaoJDBC.dropUsersTable();
        System.out.println("Таблица удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);
        System.out.println("User с id – " + id + " удален из базы данных");
    }

    public List<User> getAllUsers() {
        return userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
        System.out.println("Таблица очищена");
    }
}
