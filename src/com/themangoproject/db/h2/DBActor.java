package com.themangoproject.db.h2;

import com.themangoproject.model.*;
import java.util.List;

/**
 * This is a concrete implementation of the Actor interface. This is a
 * DataBase actor so it has limited knowledge of some DB ideas. This
 * class is meant to represent an actor and contains information
 * important to an actor such as name and roles and movies the actor
 * participated in.
 * 
 * @author Zachary Varberg
 */
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

    /**
     * This will return the db ID of this Actor
     * 
     * @return the db ID of this actor.
     */
    int getId() {
        return id;
    }

    /**
     * This will return a list of all the movies this actor has been a
     * part of.
     * 
     * @return a list of all the movies this actor has been a part of.
     */
    public List<Movie> getMovies() {
        return actorDAO.getMoviesForActor(this);
    }

    /**
     * This will return a list of all the roles this movie has
     * participated in. A role contains information such as the movie,
     * the actors role (e.g. lead actor, or supporting actress), and
     * the character played by the actor in that movie.
     * 
     * @return the list of all the roles this actor has been a part
     *         of.
     */
    public List<Role> getRoles() {
        return this.actorDAO.getRolesForActor(this);
    }

    /**
     * This will return the first name of this actor
     * 
     * @return the first name of this actor
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * This will return the last name of this actor
     * 
     * @return the last name of this actor
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * This will set the first name of this actor.
     * 
     * @param firstName
     *            the first name of this actor.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This will set the last name of this actor.
     * 
     * @param lastName
     *            the last name of this actor.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This will set the ID of this actor.
     * 
     * @param id
     *            the ID of this actor.
     */
    void setId(int id) {
        this.id = id;
    }

}
