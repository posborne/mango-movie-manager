package com.themangoproject.model;

import java.util.List;


public interface Person {

    public String getName ();

    public String getAddress ();

    public String getPhoneNumber ();

    public String getEmail ();

    public List<Movie> getOwnedMovies ();

    public List<Movie> getBorrowedMovies ();

    public void returnMovie (Movie movie);

    public void borrowMovie (Movie movie);

    public String toStirng ();

}

