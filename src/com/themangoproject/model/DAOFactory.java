package com.themangoproject.model;

/**
 * This is an interface for a DAOFactory.  A concrete implementation of this
 * class would be able to provide a DAO for any type of object for a given 
 * storage method (e.g. an H2 database, a derby database, etc.).
 * 
 * @author Paul Osborne, Zachary Varberg
 */
public interface DAOFactory {
    
    /**
     * This will return a PersonDAO.
     * 
     * @return a PersonDAO
     */
    public PersonDAO getPersonDAO ();
    
    /**
     * This will return an ActorDAO.
     * 
     * @return an ActorDAO
     */
    public ActorDAO getActorDAO ();
    
    /**
     * This will return a MovieDAO.
     * 
     * @return a MovieDAO
     */
    public MovieDAO getMovieDAO ();
    
    /**
     * This will return a ListsDAO.
     * 
     * @return a ListsDAO
     */
    public ListsDAO getListsDAO  ();
    /**
     * This will return a SetsDAO.
     * 
     * @return a SetsDAO
     */
    public SetsDAO getSetsDAO ();
}

