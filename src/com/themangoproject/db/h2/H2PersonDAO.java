package com.themangoproject.db.h2;

import com.themangoproject.model.*;
import com.themangoproject.ui.UIController;
import java.sql.*;
import java.util.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeListener;

/**
 * Data Access Object for people stored in the H2 database.
 * 
 * @author Paul Osborne
 * @version November 26, 2008
 */
public class H2PersonDAO implements PersonDAO {

    /** Singleton instance of the H2PersonDAO */
    private static H2PersonDAO instance;

    /** Database connection that the DAO will use */
    private Connection conn;

    /** Prepared statement for adding a person to the database */
    private PreparedStatement addPersonPS;
    private static final String addPersonStatement = "INSERT INTO person (`name`,`address`, `email`, `phone_num`)"
            + " VALUES (?, ?, ?, ?)";

    /** Prepared statement for updating a person */
    private PreparedStatement updatePersonPS;
    private static final String updatePersonStatement = "UPDATE person"
            + " SET name=?, address=?, email=?, phone_num=?"
            + " WHERE id=?";

    /** Prepared statement for getting a person's information */
    private PreparedStatement populatePersonPS;
    private static final String populatePersonStatement = "SELECT name, address, email, phone_num"
            + " FROM person" + " WHERE id=?";

    /** Prepared statement for retrieving all persons */
    private PreparedStatement allPersonsPS;
    private static final String allPersonsStatement = "SELECT id, name, address, email, phone_num FROM person";

    /** Prepared statement for deleting a person */
    private PreparedStatement deletePersonPS;
    private static final String deletePersonSQL = "DELETE FROM person WHERE id=?";

    /** Prepared statement for deleting a person as owner of a movie */
    private PreparedStatement deletePersonAsOwnerPS;
    private static final String deletePersonAsOwnerSQL = "UPDATE movie SET owner_id=NULL WHERE owner_id=?";

    /**
     * Prepared statement for deleting a person as borrower of a movie
     */
    private PreparedStatement deletePersonAsBorrowerPS;
    private static final String deletePersonAsBorrowerSQL = "UPDATE movie SET borrower_id=NULL WHERE borrower_id=?";

    /** Prepared statement for returning a movie */
    private PreparedStatement returnMovieForPersonPS;
    private static final String returnMovieForPersonSQL = "UPDATE movie SET borrower_id=NULL WHERE id=?";

    /** Prepared statement for borrowing a movie to a person */
    private PreparedStatement borrowMovieToPersonPS;
    private static final String borrowMovieToPersonSQL = "UPDATE movie SET borrower_id=? WHERE id=?";

    /** Prepared statement for obtaining the movies owned by a person */
    private PreparedStatement ownedMoviesForPersonPS;
    private static final String ownedMoviesForPersonSQL = "SELECT id FROM movie WHERE owner_id=?";

    /**
     * Prepared statement for obtaining the movies being borrowed by a
     * person
     */
    private PreparedStatement borrowedMoviesForPersonPS;

    private ArrayList<ChangeListener> changeListeners;
    private static final String borrowedMoviesForPersonSQL = "SELECT id FROM movie WHERE borrower_id=?";

    /**
     * The private construction for the DAO gets everything ready to
     * go, prepares statements and makes sure everything with the
     * state of the object is in line and ready to roll.
     */
    private H2PersonDAO() {
        conn = H2Util.getInstance().getConnection();
        changeListeners = new ArrayList<ChangeListener>();
        try {
            addPersonPS = conn.prepareStatement(addPersonStatement);
            updatePersonPS = conn
                    .prepareStatement(updatePersonStatement);
            populatePersonPS = conn
                    .prepareStatement(populatePersonStatement);
            allPersonsPS = conn.prepareStatement(allPersonsStatement);
            borrowedMoviesForPersonPS = conn
                    .prepareStatement(borrowedMoviesForPersonSQL);
            ownedMoviesForPersonPS = conn
                    .prepareStatement(ownedMoviesForPersonSQL);
            returnMovieForPersonPS = conn
                    .prepareStatement(returnMovieForPersonSQL);
            borrowMovieToPersonPS = conn
                    .prepareStatement(borrowMovieToPersonSQL);
            deletePersonPS = conn.prepareStatement(deletePersonSQL);
            deletePersonAsOwnerPS = conn
                    .prepareStatement(deletePersonAsOwnerSQL);
            deletePersonAsBorrowerPS = conn
                    .prepareStatement(deletePersonAsBorrowerSQL);
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
     * 
     * @param p
     *            The person that should be added to the database.
     */
    public boolean addPerson(Person p) throws PersonExistsException {
        // Check the type
        if (!(p instanceof DBPerson)) {
            throw new ClassCastException();
        }

        DBPerson person = (DBPerson) p;
        try {
            // Prepare the statement parameters
            addPersonPS.setString(1, person.getName());
            addPersonPS.setString(2, person.getAddress());
            addPersonPS.setString(3, person.getEmail());
            addPersonPS.setString(4, person.getPhoneNumber());

            // execute the statement
            addPersonPS.executeUpdate();
        } catch (SQLException ex) {
            return false;
        }
        firePersonsChangedEvent();
        return true;
    }

    /**
     * Delete the specified person from the database. If the person is
     * the owner or borrower of any movies throw an exception. In this
     * case, forceDeletePerson should be used.
     * 
     * @param p
     *            The person that should be deleted
     * @throws PersonHasMoviesException
     */
    public void deletePerson(Person p)
            throws PersonHasMoviesException {
        if (!(p instanceof DBPerson)) {
            throw new ClassCastException();
        }
        DBPerson person = (DBPerson) p;
        int id = person.getId();
        try {
            deletePersonPS.setInt(1, person.getId());
            deletePersonPS.execute();
        } catch (SQLException ex) {
            throw new PersonHasMoviesException();
        }
        if (id == UIController.getInstance().getOwnerId()) {
            UIController.getInstance().setOwnerId(-1);
        }
        firePersonsChangedEvent();
    }

    /**
     * Remove the person from any relations it is involved in and then
     * remove it from the database.
     * 
     * @param p
     *            The person that should be forcibly removed from the
     *            database.
     */
    public void forceDeletePerson(Person p) {
        if (!(p instanceof DBPerson)) {
            throw new ClassCastException();
        }
        DBPerson person = (DBPerson) p;
        try {
            int id = person.getId();
            // We want to submit statements as transaction
            conn.setAutoCommit(false);

            // prepare the statements
            deletePersonAsOwnerPS.setInt(1, person.getId());
            deletePersonAsBorrowerPS.setInt(1, person.getId());
            deletePersonPS.setInt(1, person.getId());

            // execute the updates
            deletePersonAsOwnerPS.executeUpdate();
            deletePersonAsBorrowerPS.executeUpdate();
            deletePersonPS.executeUpdate();

            // commit the transaction
            conn.commit();
            conn.setAutoCommit(true);
            if(id == UIController.getInstance().getOwnerId()){
                UIController.getInstance().setOwnerId(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        firePersonsChangedEvent();
    }

    /**
     * Update the state of the database for the person with the state
     * of the object.
     * 
     * @param person
     *            The person that should be updated.
     */
    public boolean updatePerson(Person p) {
        // Check the type
        if (!(p instanceof DBPerson)) {
            throw new ClassCastException();
        }

        DBPerson person = (DBPerson) p;
        try {
            // Prepare the statement parameters
            updatePersonPS.setString(1, person.getName());
            updatePersonPS.setString(2, person.getAddress());
            updatePersonPS.setString(3, person.getEmail());
            updatePersonPS.setString(4, person.getPhoneNumber());
            updatePersonPS.setInt(5, person.getId()); // execute the
                                                      // statement
            updatePersonPS.executeUpdate();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    /**
     * Update the internal state of the Person passed in to accurately
     * represent the state of the database.
     * 
     * @param p
     *            The person whose field values should be updated.
     * @throws PersonNotFoundException
     */
    public void populatePerson(Person p)
            throws PersonNotFoundException {
        // Check to make sure we have a DBPerson
        if (!(p instanceof DBPerson)) {
            throw new ClassCastException();
        }
        DBPerson person = (DBPerson) p;
        try {
            populatePersonPS.setInt(1, person.getId());
            ResultSet rs = populatePersonPS.executeQuery();
            if (rs.next()) {
                person.setName(rs.getString("name"));
                person.setAddress(rs.getString("address"));
                person.setEmail(rs.getString("email"));
                person.setPhoneNumber(rs.getString("phone_num"));
            } else {
                throw new PersonNotFoundException();
            }
            rs.close();
        } catch (SQLException ex) {
            // TODO: decide what needs to be done here
            ex.printStackTrace();
        }
    }

    /**
     * @return A list of all persons in the H2 database.
     */
    public List<Person> getAllPersons() {
        ArrayList<Person> persons = new ArrayList<Person>();
        try {
            allPersonsPS.execute();
            ResultSet results = allPersonsPS.getResultSet();
            while (results.next()) {
                DBPerson person = new DBPerson();
                person.setId(results.getInt("id"));
                person.setName(results.getString("name"));
                person.setAddress(results.getString("address"));
                person.setEmail(results.getString("email"));
                person.setPhoneNumber(results.getString("phone_num"));
                persons.add(person);
            }
        } catch (SQLException ex) {
            // TODO: decide what needs to be done here
            ex.printStackTrace(System.err);
        }
        return persons;
    }

    /**
     * Retrieve a list of movies that a person owns.
     * 
     * @param p
     *            Lookup movies for this person.
     * @return a list of movies that the person owns.
     */
    public List<Movie> getOwnedMovies(Person p) {
        if (!(p instanceof DBPerson)) {
            throw new ClassCastException();
        }
        DBPerson person = (DBPerson) p;
        ArrayList<Movie> movies = new ArrayList<Movie>();

        try {
            ownedMoviesForPersonPS.setInt(1, person.getId());
            ResultSet rs = ownedMoviesForPersonPS.executeQuery();
            while (rs.next()) {
                DBMovie m = new DBMovie();
                m.setId(rs.getInt("id"));
                movies.add(m);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO: handle exception
        }
        return movies;
    }

    /**
     * Retrieve a list of movies that a person is currently borrowing.
     * 
     * @param p
     *            The person of interst
     * @return a list of movies that a person is borrowing.
     */
    public List<Movie> getBorrowedMovies(Person p) {
        if (!(p instanceof DBPerson)) {
            throw new ClassCastException();
        }
        DBPerson person = (DBPerson) p;
        ArrayList<Movie> movies = new ArrayList<Movie>();

        try {
            borrowedMoviesForPersonPS.setInt(1, person.getId());
            ResultSet rs = borrowedMoviesForPersonPS.executeQuery();
            while (rs.next()) {
                DBMovie m = new DBMovie();
                m.setId(rs.getInt("id"));
                movies.add(m);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    /**
     * Return the specified movie (set it as not being borrowed).
     * 
     * @param m
     *            The movie that should be returned.
     */
    public void returnMovie(Movie m) {
        if (!(m instanceof DBMovie)) {
            throw new ClassCastException();
        }
        DBMovie movie = (DBMovie) m;

        try {
            returnMovieForPersonPS.setInt(1, movie.getId());
            returnMovieForPersonPS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Mark a person as being the one borrowing a movie. In order to
     * return a movie, <code>returnMovie()</code>.
     * 
     * @param p
     *            The person that will now be borrowing the movie.
     * @param m
     *            The movie that is being borrowed.
     */
    public void borrowMovie(Person p, Movie m) {
        if (!(p instanceof DBPerson) || !(m instanceof DBMovie)) {
            throw new ClassCastException();
        }
        DBPerson person = (DBPerson) p;
        DBMovie movie = (DBMovie) m;

        try {
            borrowMovieToPersonPS.setInt(1, person.getId());
            borrowMovieToPersonPS.setInt(2, movie.getId());
            borrowMovieToPersonPS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addPersonChangeListener(ChangeListener l) {
        changeListeners.add(l);
    }

    public void removePersonChangeListener(ChangeListener l) {
        changeListeners.remove(l);
    }

    private void firePersonsChangedEvent() {
        for (ChangeListener l : changeListeners) {
            l.stateChanged(null);
        }
    }

    public Person getPersonFromId(int ownerId) {
        DBPerson p = new DBPerson();
        p.setId(ownerId);
        try {
            this.populatePerson(p);
        } catch (PersonNotFoundException ex) {
            Logger.getLogger(H2PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;       
    }
}
