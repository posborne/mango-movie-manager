package com.themangoproject.db.h2;

import com.themangoproject.model.*;


public class H2DAOFactory implements DAOFactory {

    public PersonDAO getPersonDAO() {
        return H2PersonDAO.getInstance();
    }

    public ActorDAO getActorDAO() {
        return H2ActorDAO.getInstance();
    }

    public MovieDAO getMovieDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

