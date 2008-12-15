package com.themangoproject.model;

import java.util.List;

/**
 * This is a generic interface to represent a person.  A person consists of a 
 * a name, address, phone number, and e mail address.
 * 
 * @author Zachary Varberg, Paul Osborne
 */
public interface Person {

    /**
     * @return the name of this person
     */
    public String getName ();

    /**
     * @param name the new name of this person
     */
    public void setName(String name);

    /**
     * @return the address of this person
     */
    public String getAddress ();

    /**
     * @param address the new address of this person
     */
    public void setAddress(String address);

    /**
     * @return the phone number of this person
     */
    public String getPhoneNumber ();

    /**
     * @param phoneNumber the new phone number of this person.
     */
    public void setPhoneNumber(String phoneNumber);

    /**
     * @return the e mail of this person
     */
    public String getEmail ();

    /**
     * @param email the new email of this person
     */
    public void setEmail(String email);

    /**
     * @return a List of all the movies for which this person is the owner
     */
    public List<Movie> getOwnedMovies ();

    /**
     * @return a List of all the movies this person is borrowing
     */
    public List<Movie> getBorrowedMovies ();

    /**
     * This will return a movie that this person is currently borrowing to the 
     * owner.
     * @param movie the movie to be returned.
     */
    public void returnMovie (Movie movie);

    /**
     * This will have this person borrow a movie.
     * 
     * @param movie the movie to be borrowed.
     */
    public void borrowMovie (Movie movie);

    /**
     * @return a String representation of this object.
     */
    public String toStirng ();

}

