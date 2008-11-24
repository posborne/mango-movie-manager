package com.themangoproject.model;

import java.util.List;


public class DBActor implements Actor {

    private int id;

    public int getId () {
        return 0;
    }

    public List<Movie> getMovies() {
        throw new UnsupportedOperationException("Not supported yet.");
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

}

