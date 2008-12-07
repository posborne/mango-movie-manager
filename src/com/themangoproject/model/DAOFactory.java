package com.themangoproject.model;


public interface DAOFactory {
    public PersonDAO getPersonDAO ();
    public ActorDAO getActorDAO ();
    public MovieDAO getMovieDAO ();
    public ListsDAO getListsDAO  ();
    public SetsDAO getSetsDAO ();
}

