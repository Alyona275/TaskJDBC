package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/databaseusers";
    private final String USERNAME = "root";
    private final String PASSWORD = "MySql6648275#";

    public Connection getMyConection()
            throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public SessionFactory getSessionFactory() {
        Properties prop = new Properties();

        prop.setProperty("hibernate.connection.url", URL);
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("hibernate.connection.username", USERNAME);
        prop.setProperty("hibernate.connection.password", PASSWORD);
        prop.setProperty("hibernate.connection.pool_size", "1");
        prop.setProperty("hibernate.connection.autocommit", "false");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        prop.setProperty("show_sql", "true");

        return new Configuration().addProperties(prop).addAnnotatedClass(User.class).buildSessionFactory();
    }
}
