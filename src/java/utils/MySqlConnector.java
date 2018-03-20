/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Tito
 */
public class MySqlConnector {
    
        private static MySqlConnector instance;
        private MySqlConnector(){}
        
        public static MySqlConnector getInstance() {
            
            if (instance == null) {
                instance = new MySqlConnector();
            }
            
            return instance;
        } 
    
        public Connection getConnection() {
        // SQLite connection string
        String url = "jdbc:mysql://127.0.01:3306/videodatabase?zeroDateTimeBehavior=convertToNull";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
}
