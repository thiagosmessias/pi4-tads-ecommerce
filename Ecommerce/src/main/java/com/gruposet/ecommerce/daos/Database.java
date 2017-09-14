/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruposet.ecommerce.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiagomessias
 */
public class Database {
    private Connection connection = null;
    private String databaseUrl = "jdbc:mysql://localhost:3306/ecommerce";
    private String user = "root";
    private String psw = "root";
    
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
