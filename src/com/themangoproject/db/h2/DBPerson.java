package com.themangoproject.db.h2;

import com.themangoproject.model.*;

import java.util.List;

/**
 * This is a concrete implementation of the Person interface. This is
 * a DataBase person so it has limited knowledge of some DB ideas.
 * This class is meant to represent a person and contains information
 * important to a person such as name address, email, phone number,
 * etc.
 * 
 * @author Zachary Varberg
 * 
 */
public class DBPerson implements Person {

    private int id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private PersonDAO personDAO;
    private DBPersonState state;

    public DBPerson() {
        this.id = -1;
        this.name = null;
        this.address = null;
        this.email = null;
        this.phoneNumber = null;
        H2DAOFactory fact = new H2DAOFactory();
        personDAO = fact.getPersonDAO();
        this.state = new NotfilledPersonState();
    }

    /**
     * This will set the database ID of this person.
     * 
     * @param id
     *            the id of this person
     */
    void setId(int id) {
        this.id = id;
    }

    /**
     * This will set the name of this person
     * 
     * @param name
     *            the name of this person
     */
    public void setName(String name) {
        this.name = name;
        if (this.state instanceof NotfilledPersonState) {
        	this.state = new UpdatedPersonState();
        }
    }

    /**
     * This will set the address of this person
     * 
     * @param address
     *            the address of this person
     */
    public void setAddress(String address) {
        this.address = address;
        if (this.state instanceof NotfilledPersonState) {
        	this.state = new UpdatedPersonState();
        }
    }

    /**
     * This will set the Email address of this person
     * 
     * @param email
     *            the email address of this person
     */
    public void setEmail(String email) {
        // TODO: input validation
        this.email = email;
        if (this.state instanceof NotfilledPersonState) {
        	this.state = new UpdatedPersonState();
        }
    }

    /**
     * This will set the phone number of this person
     * 
     * @param phoneNumber
     *            the phoneNumber of this person
     */
    public void setPhoneNumber(String phoneNumber) {
        // TODO: input validation
        this.phoneNumber = phoneNumber;
        if (this.state instanceof NotfilledPersonState) {
        	this.state = new UpdatedPersonState();
        }
    }

    /**
     * This will return the database ID of this person
     * 
     * @return the database ID of this person.
     */
    public int getId() {
        return this.state.getId();
    }

    /**
     * This will return the name of this person
     * 
     * @return the name of this person
     */
    public String getName() {
        return this.state.getName();
        
    }

    /**
     * This will return this persons address
     * 
     * @return this persons address
     */
    public String getAddress() {
        return this.state.getAddress();
    }

    /**
     * This will return this persons phone number
     * 
     * @return this persons phone number
     */
    public String getPhoneNumber() {
        return this.state.getPhoneNumber();
    }

    /**
     * This will return this persons email
     * 
     * @return this persons email
     */
    public String getEmail() {
        return this.state.getEmail();
    }

    /**
     * This will return all the movies owned by this person
     * 
     * @return a list of movies owned by this person
     */
    public List<Movie> getOwnedMovies() {
        return personDAO.getOwnedMovies(this);
    }

    /**
     * This will return all of the movies this person is currently
     * borrowing.
     * 
     * @return a list of all the movies this person is borrowing.
     */
    public List<Movie> getBorrowedMovies() {
        return this.personDAO.getBorrowedMovies(this);
    }

    /**
     * This will return a movie that this person is currently
     * borrowing
     * 
     * @param movie
     *            The movie this person is returning.
     */
    public void returnMovie(Movie movie) {
        this.personDAO.returnMovie(movie);
    }

    /**
     * This will borrow a movie for this person
     * 
     * @param movie
     *            The movie this person is borrowing.
     */
    public void borrowMovie(Movie movie) {
        this.personDAO.borrowMovie(this, movie);
    }

    /**
     * This is the standard toString method. Returns a string
     * representation of this person
     * 
     * @return A string representation of this person.
     */
    public String toStirng() {
        // TODO: What do we want this to do?
        return this.getName();
    }
    
    private class UpdatedPersonState implements DBPersonState {

        @Override
        public String getAddress() {
            return DBPerson.this.address;
        }

        @Override
        public String getEmail() {
            return DBPerson.this.email;
        }

        @Override
        public int getId() {
            return DBPerson.this.id;
        }

        @Override
        public String getName() {
            return DBPerson.this.name;
        }

        @Override
        public String getPhoneNumber() {
            return DBPerson.this.phoneNumber;
        }
        
    }
    
    private class NotfilledPersonState implements DBPersonState {

        @Override
        public String getAddress() {
            try {
				DBPerson.this.personDAO.populatePerson(DBPerson.this);
			} catch (PersonNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            DBPerson.this.state = new UpdatedPersonState();
            return DBPerson.this.address;
        }

        @Override
        public String getEmail() {
            try {
				DBPerson.this.personDAO.populatePerson(DBPerson.this);
			} catch (PersonNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            DBPerson.this.state = new UpdatedPersonState();
            return DBPerson.this.email;
        }

        @Override
        public int getId() {
        	// TODO: Zach, this behavior is causing stack overflows
        	// because you must have the id to get anything, so this
        	// ends up on itself.  I am changing it to this for now
        	// -PMO
            return DBPerson.this.id;
        }

        @Override
        public String getName() {
            try {
				DBPerson.this.personDAO.populatePerson(DBPerson.this);
			} catch (PersonNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            DBPerson.this.state = new UpdatedPersonState();
            return DBPerson.this.name;
        }

        @Override
        public String getPhoneNumber() {
            try {
				DBPerson.this.personDAO.populatePerson(DBPerson.this);
			} catch (PersonNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            DBPerson.this.state = new UpdatedPersonState();
            return DBPerson.this.phoneNumber;
        }
        
    }

}
