package com.themangoproject.model;

import java.util.List;

import com.themangoproject.db.h2.DBPerson;

public interface PersonDAO {

    public void addPerson(Person person) throws PersonExistsException;
    public void updatePerson(Person person);
    public void populatePerson(Person person);
    public List<Person> getAllPersons();
    // TODO: Paul I also need this method.  I will pass a person and I need it
    // to return a list of all the movies that person owns.
    public List<Movie> getOwnedMovies(Person person);
    public List<Movie> getBorrowedMovies(DBPerson person);
    public void returnMovie(Person person, Movie movie);
    public void borrowMovie(Person person, Movie movie);
    public void getPersonInfo(Person person);

}

