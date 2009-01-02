package com.themangoproject.model;

import java.util.List;

import javax.swing.event.ChangeListener;

/**
 * This is an interface for a parson data access object (DAO). This is
 * an interface that provides methods to manipulate persons however
 * they are stored. A concrete implementation of this class would have
 * methods that are particular to the way the information is stored
 * (e.g. an H2 database, a derby databse, etc.)
 * 
 * @author Paul Osborne, Zachary Varberg
 */
public interface PersonDAO {

    /**
     * This will get thrown if someone attempts to delete a person,
     * but that person currently owns, or is borrowing a movie.
     */
    public class PersonHasMoviesException extends Exception {
        private static final long serialVersionUID = 7626640633808360430L;
    }

    /**
     * This will add a person.
     * 
     * @param person
     *            the person to store.
     * @return true if it successfully saves, false otherwise
     * @throws com.themangoproject.model.PersonExistsException
     *             if the person already exists
     */
    public boolean addPerson(Person person)
            throws PersonExistsException;

    public Person getPersonFromId(int ownerId);

    /**
     * This will save any changes made to the person passed to it.
     * 
     * @param person
     *            the person to save
     * @return true if successfully saved, false otherwise
     */
    public boolean updatePerson(Person person);

    /**
     * This will get all the information about a person from the
     * storage structure
     * 
     * @param person
     *            the person to retrieve information about.
     * @throws com.themangoproject.model.PersonNotFoundException
     *             if the person does not exist in the storage
     *             structure.
     */
    public void populatePerson(Person person)
            throws PersonNotFoundException;

    /**
     * This will return a list of all the people in the storage
     * structure
     * 
     * @return a List of all the persons in the storage structure.
     */
    public List<Person> getAllPersons();

    /**
     * This will return all the movies owned by a person
     * 
     * @param person
     *            the person to get owned movies for
     * @return A List of all the movies person owns
     */
    public List<Movie> getOwnedMovies(Person person);

    /**
     * This will return a list of all the movies a person is
     * borrowing.
     * 
     * @param person
     *            the person to get borrowed movies for
     * @return a List of all the movies person is borrowing
     */
    public List<Movie> getBorrowedMovies(Person person);

    /**
     * This will return a movie being borrowed to its owner
     * 
     * @param movie
     *            the movie to be returned to its owner
     */
    public void returnMovie(Movie movie);

    /**
     * This will lend a movie to a person
     * 
     * @param person
     *            the person borrowing the movie
     * @param movie
     *            the movie being borrowed.
     */
    public void borrowMovie(Person person, Movie movie);

    /**
     * This will delete a person if they are not in any other
     * relationships
     * 
     * @param p
     *            the person to delete
     * @throws com.themangoproject.model.PersonDAO.PersonHasMoviesException
     *             if this person is involved in relationships.
     */
    public void deletePerson(Person p)
            throws PersonHasMoviesException;

    /**
     * This will forcibly delete a person from all relationships it is
     * a part of
     * 
     * @param p
     *            the person to be deleted.
     */
    public void forceDeletePerson(Person p);

    /**
     * This will add a PersonChangeListener to this DAO
     * 
     * @param l
     *            the PersonChangeListener to add
     */
    public void addPersonChangeListener(ChangeListener l);

    /**
     * This will remove a PersonChangeListener from this DAO
     * 
     * @param l
     *            the PersonChangeListener to remove
     */
    public void removePersonChangeListener(ChangeListener l);
}
