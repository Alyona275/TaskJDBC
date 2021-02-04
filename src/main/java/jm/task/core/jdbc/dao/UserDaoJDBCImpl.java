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
            try {
                String query = "SELECT * FROM users";
                ResultSet res = statement.executeQuery(query);
            }  catch (SQLException e) {
                String sql = "CREATE TABLE users (" +
                        "dbId INT AUTO_INCREMENT NOT NULL, " +
                        "dbName VARCHAR(100) NOT NULL, " +
                        "dbLastName VARCHAR(100) NOT NULL, " +
                        "dbAge INT NOT NULL," +
                        "PRIMARY KEY (dbId))";
                statement.execute(sql);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = util.getMyConection().createStatement()) {
            try {
                String query = "SELECT * FROM users";
                statement.executeQuery(query);
                String sql = "DROP TABLE users";
                statement.execute(sql);
            }  catch (SQLException e) {

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void saveUser(String sName, String lastName, byte age) {
        String sql = "INSERT INTO users(dbName, dbLastName, dbAge) VALUES (?, ?, ?)";
        try (PreparedStatement statement = util.getMyConection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, sName);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = util.getMyConection().createStatement()) {
            String sql = "DELETE FROM users WHERE dbId = " + id;
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = util.getMyConection().createStatement()) {
            String sql = "SELECT * FROM users";
            ResultSet rs = statement.executeQuery(sql);

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
            String sql = "TRUNCATE TABLE users";
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
