package com.themangoproject.db.h2;

import com.themangoproject.model.*;
import java.util.List;


public class DBActor implements Actor {

    private int id;
    private String firstName;
    private String lastName;
    private ActorDAO actorDAO;
    
    public DBActor() {
        this(-1, null, null);
    }

    public DBActor(int id) {
        this(id, null, null);
    }
    

    public DBActor(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        H2DAOFactory fact = new H2DAOFactory();
        this.actorDAO = fact.getActorDAO();
    }
    
    
    int getId () {
        return id;
    }

    public List<Movie> getMovies() {
        return actorDAO.getMoviesForActor(this);
   }

    public List<Role> getRoles() {
        return this.actorDAO.getRolesForActor(this);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    void setId(int id) {
        this.id = id;
    }

}

