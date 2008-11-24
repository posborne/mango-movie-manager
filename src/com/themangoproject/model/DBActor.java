package com.themangoproject.model;

import java.util.List;


public class DBActor implements Actor {

    private int id;
    private String firstName;
    private String lastName;
    private List<Movie> movies;
    private List<Role> roles;

    public DBActor(int id) {
        this(id, null, null, null, null);
    }
    
    public DBActor(int id, String firstName, String lastName) {
        this(id, firstName, lastName, null, null);
    }
    
    public DBActor(int id, String firstName, String lastName, List<Movie> movies, List<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.movies = movies;
        this.roles = roles;
    }
    
    
    int getId () {
        return id;
    }

    public List<Movie> getMovies() {
        if (movies == null) {
            movies = MangoController.getInstance().getMoviesForActor(this);
        }
        return movies;
    }

    public List<Role> getRoles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getFirstName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getLastName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}

