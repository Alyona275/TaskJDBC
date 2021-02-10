package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();
    private List<User> list = new ArrayList<>();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = util.getMyConection().createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS users " +
                    "(dbId INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "dbName VARCHAR(100) NOT NULL," +
                    "dbLastName VARCHAR(100) NOT NULL," +
                    "dbAge INT NOT NULL)");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = util.getMyConection().createStatement()) {

            statement.execute("DROP TABLE IF EXISTS  users");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void saveUser(String sName, String lastName, byte age) {
        try (PreparedStatement statement = util.getMyConection()
                .prepareStatement("INSERT INTO users(dbName, dbLastName, dbAge) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, sName);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = util.getMyConection()
                .prepareStatement("DELETE FROM users WHERE dbId = ?")) {

            statement.setLong(1, id);
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = util.getMyConection().createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getLong("dbId"));
                user.setName(rs.getString("dbName"));
                user.setLastName(rs.getString("dbLastName"));
                user.setAge(rs.getByte("dbAge"));

                list.add(user);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = util.getMyConection().createStatement()) {

            statement.execute("TRUNCATE TABLE users");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
