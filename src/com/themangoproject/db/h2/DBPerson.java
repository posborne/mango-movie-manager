package com.themangoproject.db.h2;

import com.themangoproject.model.*;
import java.util.List;


public class DBPerson implements Person {

    private int id;

    public int getId () {
        return 0;
    }

    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAddress() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getPhoneNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getEmail() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List getOwnedMovies() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List getBorrowedMovies() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void returnMovie(Movie movie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void borrowMovie(Movie movie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String toStirng() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

