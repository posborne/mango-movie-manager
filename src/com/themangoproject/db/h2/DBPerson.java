package com.themangoproject.db.h2;

import com.themangoproject.model.*;
import java.util.List;


public class DBPerson implements Person {

    private int id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private PersonDAO personDAO;
    
    public DBPerson() {
        this.id = -1;
        this.name = null;
        this.address = null;
        this.email = null;
        this.phoneNumber = null;
        H2DAOFactory fact = new H2DAOFactory();
        personDAO = fact.getPersonDAO();
    }

    /**
     * This will set the database ID of this person.
     * 
     * @param id the id of this person
     */
    void setId(int id) {
        this.id = id;
    }

    /**
     * This will set the name of this person
     *      
     * @param name the name of this person
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * This will set the address of this person
     * 
     * @param address the address of this person 
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This will set the Email address of this person
     * 
     * @param email the email address of this person
     */
    public void setEmail(String email) {
        //TODO: input validation
        this.email = email;
    }

    /**
     * This will set the phone number of this person
     * 
     * @param phoneNumber the phoneNumber of this person
     */
    public void setPhoneNumber(String phoneNumber) {
        //TODO: input validation
        this.phoneNumber = phoneNumber;
    }

    /**
     * This will return the database ID of this person
     * 
     * @return the database ID of this person.
     */
    public int getId () {
        return this.id;
    }

    /**
     * This will return the name of this person
     * 
     * @return the name of this person
     */
    public String getName() {
        return this.name;
    }

    /**
     * This will return this persons address
     * 
     * @return this persons address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * This will return this persons phone number
     * 
     * @return this persons phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * This will return this persons email
     * 
     * @return this persons email
     */
    public String getEmail() {
        return this.email;
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
     * This will return all of the movies this person is currently borrowing.
     * 
     * @return a list of all the movies this person is borrowing.
     */
    public List<Movie> getBorrowedMovies() {
        return this.personDAO.getBorrowedMovies(this);
    }

    /**
     * This will return a movie that this person is currently borrowing
     * 
     * @param movie The movie this person is returning.
     */
    public void returnMovie(Movie movie) {
        this.personDAO.returnMovie(this, movie);
    }

    /**
     * This will borrow a movie for this person
     * 
     * @param movie The movie this person is borrowing.
     */
    public void borrowMovie(Movie movie) {
        this.personDAO.borrowMovie(this, movie);
    }

    /**
     * This is the standard toString method.  Returns a string
     * representation of this person
     */
    public String toStirng() {
        // TODO: What do we want this to do?
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

