package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/databaseusers";
    private final String USERNAME = "root";
    private final String PASSWORD = "MySql6648275#";

    public Connection getMyConection()
            throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
