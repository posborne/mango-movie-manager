package com.themangoproject.db.h2;

import java.sql.*;

/**
 * Set of utility operations on the H2 database.  The H2Util class is a singleton
 * and provides a single instance through the getInstance() method.  The utility
 * class guarentees that only a single connection to the database is created
 * for all users of the H2Util class.
 * 
 * @author Paul Osborne
 */
public class H2Util {

    /** Singleton instance of the H2Util class */
    private static H2Util instance;
    /** Connection to H2 database */
    private Connection conn;

    /**
     * Private constructor for the singleton.  Initializes the state of the
     * utility class.
     */
    private H2Util() {
        try {
            Class.forName("org.h2.Driver"); // load H2 driver into memory
        } catch (ClassNotFoundException ex) {
            System.err.println("Could not load H2 driver into memory");
        }
        this.conn = null;
    }

    /**
     * @return An instance of the H2Utility class for use.
     */
    public static H2Util getInstance() {
        if (instance == null) {
            instance = new H2Util();
        }
        return instance;
    }

    /**
     * @return A connection to the H2 database.
     * @throws java.sql.SQLException in the case that there is an error creating
     * a connection to the database.
     */
    private Connection connectToDb() throws SQLException {
        String url = "jdbc:h2:~/mango.db";
        return DriverManager.getConnection(url);
    }

    /**
     * Return a connection to the database.  The class is set up such that only
     * one connection to the database will be established.
     * 
     * @return A connection to the database.
     */
    public Connection getConnection() {
        try {
            return connectToDb(); // this is no longer a singleton
        } catch (SQLException ex) {
            System.err.println("There was an error creating a database connection");
        }
        return conn;
    }

    /**
     * @param values The values that should be quoted and comma separated.
     * @return A set of comma-separated quoted values from the inputs.
     */
    public static String singleQuotedCSV(String... values) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            sb.append("'" + values[i] + "'");
            if (i != values.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
