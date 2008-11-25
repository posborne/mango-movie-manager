package com.themangoproject.model;

import java.util.Date;
import java.util.List;


public class DBMovie implements Movie {

    private int id, runTime, mangoRating;
    private String director, title, rating;

    public int getId () {
        return 0;
    }

    /**
     * This will change the director of the movie.
     * 
     * @param director the new director value for this movie.
     */
    public void setDirector(String director) {
        this.director = director;
        // TODO: Add change handling here
    }

    /**
     * This will return the director of the movie
     * 
     * @return the director of this movie
     */
    public String getDirector() {
        return this.director;
    }

    /**
     * This will change the title of the movie to a new value
     * 
     * @param title the new title of the movie
     */
    public void setTitle(String title) {
        this.title = title;
        // TODO: Add change handling here
    }

    /**
     * This will return 
     * 
     * @return the title of the movie
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * This will change the rating of the movie (e.g. G, PG, PG-13, R, etc, etc).
     * 
     * @param rating the new rating of the movie (e.g. G, PG, PG-13, R, etc, etc).
     */
    public void setRating(String rating) {
        this.rating = rating;
        //TODO: Add change handling here
    }

    /**
     * This will return the rating of the movie (e.g. G, PG, PG-13, R, etc, etc).  
     */
    public String getRating() {
        return this.rating;
    }

    /**
     * This will change the run-time of the movie
     * 
     * @param length the new run-time of the movie
     */
    public void setRunTime(int runTime) {
        this.runTime = runTime;
        // TODO: Add change handling here.
    }

    /**
     * This will return the run-time of the movie
     * 
     * @return the run-time of the movie
     */
    public int getRunTime() {
        return this.runTime;
    }

    /**
     * This will change the mango rating (similar to star ratings, e.g. 0-5 mangoes 
     * is roughly the same as 0-5 stars).
     * 
     * @param mangoRating the new mango rating, an int between 0-10
     */
    public void setMangoRating(int mangoRating) {
        if(mangoRating <= 10 && mangoRating >= 0){
        	this.mangoRating = mangoRating;
        } else {
        	throw new IllegalArgumentException("Mango rating must be between 0 and 10");
        }
        // TODO: Add change handling here
    }

    /**
     * This will return the mango rating of this movie
     *  
     * @return the mango rating of this movie
     */
    public int getMangoRating() {
        return this.mangoRating;
    }

    /** 
     * This will return all the actors who were a part of this movie
     * 
     * @return all the actors in this movie
     */
    public List<Actor> getActors() {
    	// TODO: Request Actors from DAO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    /**
     * This will add an actor to be associated with this movie
     * 
     * @param actor the actor to be associated with this movie
     */
    public void addActor(Actor actor) {
    	// TODO: Should this be a role instead of an actor?
        throw new UnsupportedOperationException("Not suppor ted yet.");
    }

    /**
     * This will remove the association of an actor with this movie
     * 
     * @param actor the actor to be removed
     */
    public void removeActor(Actor actor) {
    	// TODO: Should this be a role?
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * True if information has changed since loaded from the database
     * false otherwise.
     * 
     * @return true if there has been a change in the information since it was
     * loaded from the database.
     */
    public boolean hasChanged() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getRuntime() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getYear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getASIN() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date getPurchaseDate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getCustomDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Person getOwner() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Person getBorrower() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<String> getGenres() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

