package com.themangoproject.db.h2;

import com.themangoproject.model.*;

/**
 * This is a concrete implementation of the Role interface. This is a
 * DataBase role so it has limited knowledge of some DB ideas. This
 * class is meant to represent a role and contains information
 * important to a role such as the actor, movie, and character that
 * defines this role.
 * 
 * @author Zachary Varberg
 * 
 */
public class DBRole implements Role {

    private int id, movieID, actorID;
    private String character, role;


    public DBRole(){

    }
    
    /**
     * This will return the actor that is a part of this role.
     * 
     * @return the actor that is a part of this Role.
     */
    public Actor getActor() {
        DBActor a = new DBActor();
        a.setId(this.actorID);
        H2ActorDAO.getInstance().populateActor(a);
        return a;
    }
    
    /**
     * This will set the id of this role
     * 
     * @param id the id of this role
     */
    void setId(int id){
        this.id = id;
    }
    
    /**
     * This will return the id of the movie this role is a part of.
     * 
     * @return the id of the movie this role is a part of.
     */
    int getMovieId(){
        return this.movieID;
    }
    
    /**
     * This will return the id of the actor this role is a part of.
     * 
     * @return the id of the actor this role is a part of.
     */
    int getActorId(){
        return this.actorID;
    }
    
    /**
     * This will set the id of the movie this role is a part of.
     * 
     * @param movieID the id of the movie this role is a part of.
     */
    void setMovieId(int movieID){
        this.movieID = movieID;
    }
    
    /**
     * This will set the id of the actor this role is a part of.
     * 
     * @param movieID the id of the actor this role is a part of.
     */
    void setActorId(int actorID){
        this.actorID = actorID;
    }
    
    /**
     * This will return the db ID of this role
     * 
     * @return the id of this role.
     */
    int getId(){
        return this.id;
    }

    /**
     * This will return the character name of the character played by
     * the actor in this role.
     * 
     * @return the character played by the actor in this role
     */
    public String getCharacter() {
        return this.character;
    }

    /**
     * This will return the role of this role (e.g. leading actor, or supporting
     * actress).
     * 
     * @return the role of this role.
     */
    public String getRole() {
        return this.role;
    }

    /**
     * This will return the movie that this role is a part of.
     * 
     * @return the movie this role is a part of.
     */
    public Movie getMovie() {
      //TODO: fix me!
        return H2MovieDAO.getInstance().getMovieFromId(this.movieID);
    }

    /**
     * This will set the character (name) for this role.
     * 
     * @param character the character name of this role.
     */
    @Override
    public void setCharacter(String character) {
        this.character = character;
    }

    /**
     * This will set the role  of this role (e.g. leading actor, or supporting
     * actress)
     */
    @Override
    public void setRole(String roleName) {
        this.role = roleName;
    }

}
