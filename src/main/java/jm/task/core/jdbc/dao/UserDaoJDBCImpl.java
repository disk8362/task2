package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {
    }
    //----------------------------------------------------
    public void createUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            String create = "CREATE TABLE Users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20),lastName VARCHAR(20), age INT)";
            statement.executeUpdate(create);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------------------
    public void dropUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        String drop = "DROP TABLE USERS";
        try {
            statement.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //-----------------------------------------------
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement statement = null;
        try {
            String save = "INSERT INTO Users( name, lastName, age) Values (?,?,?) ";
            statement = connection.prepareStatement(save);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeUserById(long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            String del = "DELETE FROM Users WHERE Id = ? ";
            statement = connection.prepareStatement(del);
            statement.setByte(1, (byte) id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        String all = "SELECT id, name, lastName, age FROM Users";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(all);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            System.out.println(userList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public void cleanUsersTable() throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
