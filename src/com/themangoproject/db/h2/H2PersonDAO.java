package com.themangoproject.db.h2;

import com.themangoproject.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for people stored in the H2 database.
 *
 * @author Paul Osborne
 * @version November 26, 2008
 */
public class H2PersonDAO implements PersonDAO {

    /** Singleton instancce of the H2PersonDAO */
    private static H2PersonDAO instance;
    /** Database connection that the DAO will use */
    private Connection conn;
    /** Prepared statement for adding a person to the database */
    private PreparedStatement addPersonPS;
    private static final String addPersonStatement =
            "INSERT INTO person (`name`,`address`, `email`, `phone_num`)" +
            " VALUES (?, ?, ?, ?)";
    /** Prepared statement for updating a person */
    private PreparedStatement updatePersonPS;
    private static final String updatePersonStatement =
            "UPDATE person" +
            " SET name=?, address=?, email=?, phone_num=?" +
            " WHERE id=?";
    /** Prepared statement for getting a person's information */
    private PreparedStatement populatePersonPS;
    private static final String populatePersonStatement =
            "SELECT name, address, email, phone_num" +
            " FROM person" +
            " WHERE id=?";
    /** Prepared statement for retrieving all persons */
    private PreparedStatement allPersonsPS;
    private static final String allPersonsStatement =
            "SELECT id, name, address, email, phone_num FROM person";

    /**
     * The private construction for the DAO gets everything ready to go,
     * prepares statements and makes sure everything with the state of the
     * object is in line and ready to roll.
     */
    private H2PersonDAO() {
        conn = H2Util.getInstance().getConnection();
        try {
            addPersonPS = conn.prepareStatement(addPersonStatement);
            updatePersonPS = conn.prepareStatement(updatePersonStatement);
            populatePersonPS = conn.prepareStatement(populatePersonStatement);
            allPersonsPS = conn.prepareStatement(allPersonsStatement);
        } catch (SQLException ex) {
            // TODO: decide what needs to be done here
            ex.printStackTrace(System.err);
        }
    }

    /**
     * @return An instance of the H2PersonDAO singleton.
     */
    public static H2PersonDAO getInstance() {
        if (instance == null) {
            instance = new H2PersonDAO();
        }
        return instance;
    }

    /**
     * Add the person specified to the database.
     * @param p The person that should be added to the database.
     */
    public void addPerson(Person p) throws PersonExistsException {
        // Check the type
        if (!(p instanceof DBPerson)) {
            throw new ClassCastException();
        }

        DBPerson person = (DBPerson) p;
        if (person.getId() == -1) {
            // TODO: this might be changed to Integer
            throw new PersonExistsException();
        }

        try {
            // Prepare the statement parameters
            addPersonPS.setString(0, person.getName());
            addPersonPS.setString(1, person.getAddress());
            addPersonPS.setString(2, person.getEmail());
            addPersonPS.setString(3, person.getPhoneNumber());

            // execute the statement
            addPersonPS.execute();
        } catch (SQLException ex) {
            // TODO: decide what needs to be done here
            ex.printStackTrace(System.err);
        }
    }

    /**
     * 
     * @param person The person that should be updated.
     */
    public void updatePerson(Person p) {
        // Check the type
        if (!(p instanceof DBPerson)) {
            throw new ClassCastException();
        }

        DBPerson person = (DBPerson) p;
        try {
            // Prepare the statement parameters
            updatePersonPS.setString(0, person.getName());
            updatePersonPS.setString(1, person.getAddress());
            updatePersonPS.setString(2, person.getEmail());
            updatePersonPS.setString(3, person.getPhoneNumber());
            updatePersonPS.setInt(4, person.getId()); // execute the statement
            updatePersonPS.execute();
        } catch (SQLException ex) {
            // TODO: decide what needs to be done here
            ex.printStackTrace(System.err);
        }
    }

    /**
     * 
     * @param p The person whose field values should be updated.
     */
    public void populatePerson(Person p) {
        // Check to make sure we have a DBPerson
        if (!(p instanceof DBPerson)) {
            throw new ClassCastException();
        }
        DBPerson person = (DBPerson) p;
        try {
            populatePersonPS.setInt(0, person.getId());
        } catch (SQLException ex) {
            // TODO: decide what needs to be done here
            ex.printStackTrace(System.err);
        }
    }

    /**
     * 
     * @return
     */
    public List<Person> getAllPersons() {
        ArrayList<Person> persons = new ArrayList<Person>();
        try {
            allPersonsPS.execute();
            ResultSet results = allPersonsPS.getResultSet();
            while (results.next()) {
                DBPerson person = new DBPerson();
                // TODO: implement me
            }
        } catch (SQLException ex) {
            Logger.getLogger(H2PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }
}

