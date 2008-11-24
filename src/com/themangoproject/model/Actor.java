package com.themangoproject.model;

import java.util.List;


public interface Actor {

    public List<Movie> getMovies ();

    public List<Role> getRoles ();

    public String getFirstName ();

    public String getLastName ();

    public String toString ();

}
