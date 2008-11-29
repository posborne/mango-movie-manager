/**
 * 
 */
package com.themangoproject.model;

import java.util.List;

/**
 * @author Zachary Varberg
 *
 */
public class DBPerson implements Person {
    
    private String address;
    private MangoController controller;
    
    public void DBPerson(){
        this.controller = MangoController.getInstance();
    }
    
    
    /**
     * This will add this movie to this persons list of borrowed movies
     * 
     * @param movie the movie to be borrowed.
     */
    @Override
    public void borrowMovie(Movie movie) {
        // TODO: Should we store a list in this class or just go to
        // the database?  Or should this method be in the movie class?

    }
    
    /**
     * This will remove this movie from the list of movies being borrowed
     * by this person.
     * 
     * @param the movie to be returned
     */
    public void returnMovie(Movie movie) {
        //TODO: return this movie somehow.
    }

    /* (non-Javadoc)
     * @see com.themangoproject.model.Person#getAddress()
     */
    @Override
    public String getAddress() {
        // TODO right way to handle this?
        if(this.address == null){
            controller.getPersonInfo(this);
        }
        return this.address;
    }

    /* (non-Javadoc)
     * @see com.themangoproject.model.Person#getBorrowedMovies()
     */
    @Override
    public List<Movie> getBorrowedMovies() {
        // TODO Again go to the database or store here?
        return null;
    }

    /* (non-Javadoc)
     * @see com.themangoproject.model.Person#getEmail()
     */
    @Override
    public String getEmail() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.themangoproject.model.Person#getName()
     */
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.themangoproject.model.Person#getOwnedMovies()
     */
    @Override
    public List<Movie> getOwnedMovies() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.themangoproject.model.Person#getPhoneNumber()
     */
    @Override
    public String getPhoneNumber() {
        // TODO Auto-generated method stub
        return null;
    }


    /* (non-Javadoc)
     * @see com.themangoproject.model.Person#setAddress(java.lang.String)
     */
    @Override
    public void setAddress(String address) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.themangoproject.model.Person#setEmail(java.lang.String)
     */
    @Override
    public void setEmail(String email) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.themangoproject.model.Person#setName(java.lang.String)
     */
    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.themangoproject.model.Person#setPhoneNumber(java.lang.String)
     */
    @Override
    public void setPhoneNumber(String phoneNumber) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.themangoproject.model.Person#toStirng()
     */
    @Override
    public String toStirng() {
        // TODO Auto-generated method stub
        return null;
    }

}
