package org.payments.repository;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class DBManager {
    private static volatile DataSource dataSource;
    private  final String connectionUrl;
    private final String user;
    private final String password;
    private static DBManager manager;


    private DBManager() {
        ResourceBundle bundle = ResourceBundle.getBundle("db_configuration", Locale.getDefault());
        connectionUrl = bundle.getString("connection.url");
        user = bundle.getString("user");
        password = bundle.getString("password");
    }

    public static DBManager getInstance() {
        if (manager == null){
            manager = new DBManager();
        }
        return manager;
    }

    public synchronized Connection getConnection() throws SQLException {
        return manager.createConnection();
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, user,password);
    }
}
