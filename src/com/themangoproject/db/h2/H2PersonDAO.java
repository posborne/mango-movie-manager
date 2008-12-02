package com.themangoproject.db.h2;

import com.themangoproject.model.*;

import java.sql.*;
import java.util.*;

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

	/**
	 * The private construction for the DAO gets everything ready to go,
	 * prepares statements and makes sure everything with the state of the
	 * object is in line and ready to roll.
	 */
	private H2PersonDAO() {
		conn = H2Util.getInstance().getConnection();
		try {
			addPersonPS = conn.prepareStatement(addPersonStatement);
			updatePersonPS = conn
					.prepareStatement(updatePersonStatement);
			populatePersonPS = conn
					.prepareStatement(populatePersonStatement);
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
	 * 
	 * @param p
	 *            The person that should be added to the database.
	 */
	public void addPerson(Person p) throws PersonExistsException {
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
			// TODO: decide what needs to be done here
			ex.printStackTrace(System.err);
		}
	}

	/**
	 * Update the state of the database for the person with the state of the
	 * object.
	 * 
	 * @param person
	 *            The person that should be updated.
	 */
	public void updatePerson(Person p) {
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
			updatePersonPS.setInt(5, person.getId()); // execute the statement
			updatePersonPS.executeUpdate();
		} catch (SQLException ex) {
			// TODO: decide what needs to be done here
			ex.printStackTrace(System.err);
		}
	}

	/**
	 * Update the internal state of the Person passed in to accurately represent
	 * the state of the database.
	 * 
	 * @param p
	 *            The person whose field values should be updated.
	 */
	public void populatePerson(Person p) {
		// Check to make sure we have a DBPerson
		if (!(p instanceof DBPerson)) {
			throw new ClassCastException();
		}
		DBPerson person = (DBPerson) p;
		try {
			populatePersonPS.setInt(1, person.getId());
			ResultSet rs = populatePersonPS.executeQuery();
			rs.next();
			person.setName(rs.getString("name"));
			person.setAddress(rs.getString("address"));
			person.setEmail(rs.getString("email"));
			person.setPhoneNumber(rs.getString("phone_num"));
			rs.close();
		} catch (SQLException ex) {
			// TODO: decide what needs to be done here
			ex.printStackTrace(System.err);
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
				person
						.setPhoneNumber(results
								.getString("phone_num"));
				persons.add(person);
			}
		} catch (SQLException ex) {
			// TODO: decide what needs to be done here
			ex.printStackTrace(System.err);
		}
		return persons;
	}

	// TODO: Paul I also need this method.  I will pass a person and I need it
    // to return a list of all the movies that person owns.
    @Override
    public List<Movie> getOwnedMovies(Person person) {
        // TODO Auto-generated method stub
        return null;
    }

    
    // TODO: Paul I also need this method.  I will pass a person and I need it
    // to return a list of all the movies that person is borrowing.
    @Override
    public List<Movie> getBorrowedMovies(DBPerson person) {
        // TODO Auto-generated method stub
        return null;
    }

    // TODO: Paul I need this one as well.  I will pass a movie, and a person.
    // What is happening is that the person passed is currently borrowing
    // the movie passed and is now returning it.  If the person is not actually
    // borrowing that movie it should throw some sort of exception.
    @Override
    public void returnMovie(Person person, Movie movie) {
        // TODO Auto-generated method stub
        
    }

    // TODO: What can I say Paul, I am a demanding guy.  In this case I am 
    // passing a person and a movie.  The person passed is borrowing the 
    // movie passed.  If that movie is already being borrowed by someone
    // else, this should probably throw some sort of exception.
    @Override
    public void borrowMovie(Person person, Movie movie) {
        // TODO Auto-generated method stub
        
    }

    //TODO: Another one for you Paul!
    public Person getPersonFromId(int Id) {
        // TODO Auto-generated method stub
        return null;
    }
}
