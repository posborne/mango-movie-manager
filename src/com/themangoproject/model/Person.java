package com.themangoproject.model;

import java.util.List;


public interface Person {

    public String getName ();

    public void setName(String name);

    public String getAddress ();

    public void setAddress(String address);

    public String getPhoneNumber ();

    public void setPhoneNumber(String phoneNumber);

    public String getEmail ();

    public void setEmail(String email);

    public List<Movie> getOwnedMovies ();

    public List<Movie> getBorrowedMovies ();

    public void returnMovie (Movie movie);

    public void borrowMovie (Movie movie);

    public String toStirng ();

}

