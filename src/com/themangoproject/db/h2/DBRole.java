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

    private int id, movieID = -1, actorID = -1;
    private String character, role;
    private DBRoleState state;
    private ActorDAO actorDAO; 
    


    public DBRole(){
        H2DAOFactory fact = new H2DAOFactory();
        this.actorDAO = fact.getActorDAO();
        this.state = new NotfilledRoleState();
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
        return this.state.getCharacter();
    }

    /**
     * This will return the role of this role (e.g. leading actor, or supporting
     * actress).
     * 
     * @return the role of this role.
     */
    public String getRole() {
        return this.state.getRole();
    }

    /**
     * This will return the movie that this role is a part of.
     * 
     * @return the movie this role is a part of.
     */
    public Movie getMovie() {
    	DBMovie movie = new DBMovie();
    	movie.setId(this.movieID);
        return movie;
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
    
    private class NotfilledRoleState implements DBRoleState {

        @Override
        public String getCharacter() {
            if (id != -1) {
                try {
					DBRole.this.actorDAO.populateRole(DBRole.this);
				} catch (RoleNotFoundException e) {
					DBRole.this.role = null;
					DBRole.this.character = null;
				}
                DBRole.this.state = new UpdatedRoleState();
            }
            return DBRole.this.character;
        }

        @Override
        public String getRole() {
            if (id != -1) {
            	try {
					DBRole.this.actorDAO.populateRole(DBRole.this);
				} catch (RoleNotFoundException e) {
					DBRole.this.role = null;
					DBRole.this.character = null;
				}
                DBRole.this.state = new UpdatedRoleState();
            }
            return DBRole.this.role;
        }
        
        
    }
    
    private class UpdatedRoleState implements DBRoleState {

        @Override
        public String getCharacter() {
            return DBRole.this.character;
        }

        @Override
        public String getRole() {
            return DBRole.this.role;
        }
        
        
    }
}
