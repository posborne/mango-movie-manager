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
        return H2MovieDAO.getInstance();
    }

    public ListsDAO getListsDAO() {
        return H2ListsDAO.getInstance();
    }

    public SetsDAO getSetsDAO() {
        return H2SetsDAO.getInstance();
    }

}
