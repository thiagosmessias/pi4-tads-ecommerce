package com.gruposet.ecommerce.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private Connection connection = null;
    private final String databaseUrl = "jdbc:mysql://localhost:3306/ecommerce";
    private final String user = "root";
    private final String psw = "";
    
    private Connection createConnection() {
        Properties properties = new Properties();
        properties.put("user", user);
        properties.put("password", psw);

        try {
            connection = DriverManager.getConnection(databaseUrl, properties);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    // TODO: Error validations
    public Connection getConnection() {
        if (connection == null) {
            return this.createConnection();
        }
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
