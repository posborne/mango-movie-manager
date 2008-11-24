package com.themangoproject.model;


public class H2DAOFactory implements DAOFactory {

    public void getDAO (int daoType) {
    }

    public PersonDAO getPersonDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ActorDAO getActorDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public MovieDAO getMovieDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

