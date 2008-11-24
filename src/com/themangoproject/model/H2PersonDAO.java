package com.themangoproject.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class H2PersonDAO implements PersonDAO {

    public void addPerson(String name, String address, long phoneNumber, String email) {
        String query =
                "INSERT INTO person (`name`,`address`, `email`, `phone_num`)" +
                " VALUES (" +
                H2Util.singleQuotedCSV(name, address, email) + ", " + phoneNumber + ")";

        Connection conn = H2Util.getInstance().getConnection();
        try {
            Statement addPersonStatement = conn.createStatement();
            addPersonStatement.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(H2PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePerson(Person person) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void Unnamed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

