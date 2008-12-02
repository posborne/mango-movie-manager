package com.themangoproject.model;

import java.util.List;


public interface PersonDAO {

    public void addPerson(Person person) throws PersonExistsException;
    public void updatePerson(Person person);
    public void populatePerson(Person person);
    public List<Person> getAllPersons();
    // TODO: Paul I also need this method.  I will pass a person and I need it
    // to return a list of all the movies that person owns.
    public List<Movie> getOwnedMovies(Person person);
    public List<Movie> getBorrowedMovies(Person person);
    public void returnMovie(Movie movie);
    public void borrowMovie(Person person, Movie movie);
    public void getPersonInfo(Person person);

}

