package com.themangoproject.db.h2;

import com.themangoproject.model.*;
import java.util.Date;
import java.util.List;


public class DBMovie implements Movie {

    private Integer id, runtime, mangoRating, year;
    private String director, title, rating, ASIN, customDescription;
    private boolean changed;
    private Date purchaseDate;

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
        this.changed = true;
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
        this.changed = true;
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
        this.changed = true;
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
    public void setRuntime(int runtime) {
        this.runtime = runtime;
        this.changed = true;
        // TODO: Add change handling here.
    }

    /**
     * This will return the run-time of the movie
     * 
     * @return the run-time of the movie
     */
    public int getRuntime() {
        return this.runtime;
    }

    /**
     * This will change the mango rating (similar to star ratings, e.g. 0-5 mangoes 
     * is roughly the same as 0-5 stars).
     * 
     * @param mangoRating the new mango rating, an int between 0-10
     * @throws IllegalArgumentException if the mango rating is not an integer between
     * 0 and 10
     */
    public void setMangoRating(int mangoRating) throws IllegalArgumentException {
        if(mangoRating <= 10 && mangoRating >= 0){
        	this.mangoRating = mangoRating;
        	this.changed = true;
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
    	if (mangoRating != null) {
			return this.mangoRating;
		} else {
			//TODO: load info from Controller			
			return this.mangoRating;
		}
    }

    /** 
     * This will return all the actors who were a part of this movie
     * 
     * @return all the actors in this movie
     */
    public List<Actor> getActors() {
    	// TODO: Request Actors from Controller
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    /**
     * This will add an actor to be associated with this movie
     * 
     * @param actor the actor to be associated with this movie
     */
    public void addActor(Actor actor) {
    	// TODO: Should this be a role instead of an actor?
    	this.changed = true;
        throw new UnsupportedOperationException("Not suppor ted yet.");
    }

    /**
     * This will remove the association of an actor with this movie
     * 
     * @param actor the actor to be removed
     */
    public void removeActor(Actor actor) {
    	// TODO: Should this be a role?
    	this.changed = true;
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
        return changed;
    }

//    /**
//     * This will return the 
//     * 
//     */
//    public int getRuntime() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }

    /** 
     * This will return the year the movie was released
     * 
     * @return the year the movie was released.
     */
    public int getYear() {
    	return this.year;
    }
    
    
    /**
     * This will set the year the movie was released
     * 
     * @param year the year the movie was released
     * @throws IllegalArgumentException if the year is not a valid year i.e.
     * if it is not an int between 1890 and 9999
     */
    public void setYear(int year) throws IllegalArgumentException {
    	if(year < 10000 && year > 1890){
    		this.year = year;
    		this.changed = true;
    	} else {
    		throw new IllegalArgumentException("Not a valid year;");
    	}
    }

    /**
     * This will return the ASIN (Amazon System? Indentification Number)
     * 
     * @return the ASIN of this movie
     */
    public String getASIN() {
        return this.ASIN;
    }
    
    /**
     * This will change the ASIN for this movie
     * 
     * @param ASIN the ASIN to set for this movie
     */
    public void setASIN(String ASIN) {
    	this.ASIN = ASIN;
    	this.changed = true;
    	//TODO: Add change handling here    	
    }

    /** 
     * This will return the date this movie was purchased.
     * 
     * @return Date the date this movie was purchased
     * 
     */
    public Date getPurchaseDate() {
        return this.purchaseDate;
    }
    
    /**
     * This will set the purchase date of this movie
     * 
     * @param purchaseDate the date this movie was purchased
     */
    public void setPurchaseDate(Date purchaseDate){
    	this.purchaseDate = purchaseDate;
    	changed = true;
    	//TODO: Change handling here
    }

    /**
     * This will return the custom description of this movie as described
     * by the owner
     * 
     * @return CustomDescription is the custom description of this movie 
     */
    public String getCustomDescription() {
        return this.customDescription;
    }
    
    /**
     * This will set the custom description of this movie
     * 
     * @param customDescription the new custom description of this movie
     */
    public void setCustomDescription(String customDescription){
    	this.customDescription = customDescription;
    	this.changed = true;
    	//TODO: Change handling here
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

    public String getCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    int getOwnerId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    int getBorrowerId() {
        throw new UnsupportedOperationException();
    }

}

