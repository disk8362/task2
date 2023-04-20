package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Petrov", (byte) 19);
        userService.saveUser("Petr", "Ivanov", (byte) 29);
        userService.saveUser("Sergey", "Sidorov", (byte) 38);
        userService.saveUser("Oleg", "Popov", (byte) 22);
        userService.getAllUsers();
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
