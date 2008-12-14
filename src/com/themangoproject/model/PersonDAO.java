package com.themangoproject.model;

import java.util.List;

import javax.swing.event.ChangeListener;


public interface PersonDAO {

    public class PersonHasMoviesException extends Exception {
		private static final long serialVersionUID = 7626640633808360430L;
	}

    public boolean addPerson(Person person) throws PersonExistsException;
    public boolean updatePerson(Person person);
    public void populatePerson(Person person) throws PersonNotFoundException;
    public List<Person> getAllPersons();
    public List<Movie> getOwnedMovies(Person person);
    public List<Movie> getBorrowedMovies(Person person);
    public void returnMovie(Movie movie);
    public void borrowMovie(Person person, Movie movie);
    public void deletePerson(Person p) throws PersonHasMoviesException;
    public void forceDeletePerson(Person p);
	public void addPersonChangeListener(ChangeListener l);
	public void removePersonChangeListener(ChangeListener l);
}

