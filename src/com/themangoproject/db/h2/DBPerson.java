package com.themangoproject.db.h2;

import com.themangoproject.model.*;
import java.util.List;


public class DBPerson implements Person {

    private int id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    
    public DBPerson() {
        this.id = -1;
        this.name = null;
        this.address = null;
        this.email = null;
        this.phoneNumber = null;
    }

    void setId(int id) {
        this.id = id;
    }

    void setName(String name) {
        this.name = name;
    }

    void setAddress(String address) {
        this.address = address;
    }

    void setEmail(String email) {
        this.email = email;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

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

