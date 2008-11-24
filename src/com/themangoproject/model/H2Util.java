package com.themangoproject.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paul Osborne
 */
public class H2Util {

    private static H2Util instance;
    private Connection conn;
    
    private H2Util() {
        
    }
    
    public static H2Util getInstance() {
        if (instance == null) {
            instance = new H2Util();
        }
        return instance;
    }
    
    private Connection connectToDb() throws SQLException {
        String url = "jdbc:h2:~/mango.db";
        return DriverManager.getConnection(url);
    }
    
    public Connection getConnection() {
        if (conn == null) {
            try {
                conn = connectToDb();
            } catch (SQLException ex) {
               System.err.println("Could not connect to H2 DB: " + ex);
            }
        }
        return conn;
    }
    
}
