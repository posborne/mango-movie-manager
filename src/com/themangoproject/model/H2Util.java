package com.themangoproject.model;

import java.sql.*;

/**
 *
 * @author Paul Osborne
 */
public class H2Util {

    private static H2Util instance;
    private Connection conn;
    
    private H2Util() {
        
    }
    
    private H2Util getInstance() {
        if (instance == null) {
            instance = new H2Util();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return null;
    }
    
}
