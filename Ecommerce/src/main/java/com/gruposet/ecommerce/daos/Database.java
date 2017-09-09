package com.gruposet.ecommerce.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private Connection connection = null;
    private String databaseUrl = "jdbc:mysql://localhost:3306/ecommerce";
    private String user = "root";
    private String psw = "root";

    public Database() {
        Properties properties = new Properties();
        properties.put("user", user);
        properties.put("password", psw);
        try {
            connection = DriverManager.getConnection(databaseUrl, properties);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
