package com.themangoproject.model;

import java.util.Date;
import java.util.List;

/**
 * This is a concrete implementation of the movie interface. This is
 * an implementation that has knowledge about some limited database
 * knowledge (e.g. the unique DB id). It models a movie containing all
 * the information about that movie.
 * 
 * @author Zachary Varberg
 * 
 */
public class DBMovie implements Movie {

    private Integer id, runtime, mangoRating, year;
    private String director, title, rating, ASIN, customDescription,
            type, condition;
    private Date purchaseDate;
    private Person owner, borrower;
    private MangoController controller;

    public DBMovie() {
        controller = MangoController.getInstance();
    }

    /**
     * This will return the Database unique ID of this movie
     * 
     * @return the DB ID of this movie
     */
    public int getId() {
        if (id == null) {
            controller.getMovieInfo(this);
        }
        return id;
    }

    /**
     * This will change the director of the movie.
     * 
     * @param director
     *            the new director value for this movie.
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
        if (director == null) {
            controller.getMovieInfo(this);
        }
        return this.director;
    }

    /**
     * This will change the title of the movie to a new value
     * 
     * @param title
     *            the new title of the movie
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
        if (title == null) {
            controller.getMovieInfo(this);
        }
        return this.title;
    }

    /**
     * This will change the rating of the movie (e.g. G, PG, PG-13, R,
     * etc, etc).
     * 
     * @param rating
     *            the new rating of the movie (e.g. G, PG, PG-13, R,
     *            etc, etc).
     */
    public void setRating(String rating) {
        this.rating = rating;
        // TODO: Add change handling here
    }

    /**
     * This will return the rating of the movie (e.g. G, PG, PG-13, R,
     * etc, etc).
     */
    public String getRating() {
        if (rating == null) {
            controller.getMovieInfo(this);
        }
        return this.rating;
    }

    /**
     * This will change the run-time of the movie
     * 
     * @param length
     *            the new run-time of the movie
     */
    public void setRuntime(int runtime) {
        this.runtime = runtime;
        // TODO: Add change handling here.
    }

    /**
     * This will return the run-time of the movie
     * 
     * @return the run-time of the movie
     */
    public int getRuntime() {
        if (runtime == null) {
            controller.getMovieInfo(this);
        }
        return this.runtime;
    }

    /**
     * This will change the mango rating (similar to star ratings,
     * e.g. 0-5 mangoes is roughly the same as 0-5 stars).
     * 
     * @param mangoRating
     *            the new mango rating, an int between 0-10
     * @throws IllegalArgumentException
     *             if the mango rating is not an integer between 0 and
     *             10
     */
    public void setMangoRating(int mangoRating)
            throws IllegalArgumentException {
        if (mangoRating <= 10 && mangoRating >= 0) {
            this.mangoRating = mangoRating;
        } else {
            throw new IllegalArgumentException(
                    "Mango rating must be between 0 and 10");
        }
        // TODO: Add change handling here
    }

    /**
     * This will return the mango rating of this movie
     * 
     * @return the mango rating of this movie
     */
    public int getMangoRating() {
        if (mangoRating == null) {
            controller.getMovieInfo(this);
        }
        return this.mangoRating;
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
     * @param actor
     *            the actor to be associated with this movie
     */
    public void addActor(Actor actor) {
        // TODO: Should this be a role instead of an actor?
        throw new UnsupportedOperationException("Not suppor ted yet.");
    }

    /**
     * This will remove the association of an actor with this movie
     * 
     * @param actor
     *            the actor to be removed
     */
    public void removeActor(Actor actor) {
        // TODO: Should this be a role?
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // /**
    // * True if information has changed since loaded from the
    // database
    // * false otherwise.
    // *
    // * @return true if there has been a change in the information
    // since it was
    // * loaded from the database.
    // */
    // public boolean hasChanged() {
    // return changed;
    // }
    //
    // /**
    // * This will return the
    // *
    // */
    // public int getRuntime() {
    // throw new UnsupportedOperationException("Not supported yet.");
    // }

    /**
     * This will return the year the movie was released
     * 
     * @return the year the movie was released.
     */
    public int getYear() {
        if (this.year == null) {
            controller.getMovieInfo(this);
        }
        return this.year;
    }

    /**
     * This will set the year the movie was released
     * 
     * @param year
     *            the year the movie was released
     * @throws IllegalArgumentException
     *             if the year is not a valid year i.e. if it is not
     *             an int between 1890 and 9999
     */
    public void setYear(int year) throws IllegalArgumentException {
        if (year < 10000 && year > 1890) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("Not a valid year;");
        }
    }

    /**
     * This will return the ASIN (Amazon System? Indentification
     * Number)
     * 
     * @return the ASIN of this movie
     */
    public String getASIN() {
        if (this.ASIN == null) {
            controller.getMovieInfo(this);
        }
        return this.ASIN;
    }

    /**
     * This will change the ASIN for this movie
     * 
     * @param ASIN
     *            the ASIN to set for this movie
     */
    public void setASIN(String ASIN) {
        this.ASIN = ASIN;
        // TODO: Add change handling here
    }

    /**
     * This will return the date this movie was purchased.
     * 
     * @return Date the date this movie was purchased
     * 
     */
    public Date getPurchaseDate() {
        if (this.purchaseDate == null) {
            controller.getMovieInfo(this);
        }
        return this.purchaseDate;
    }

    /**
     * This will set the purchase date of this movie
     * 
     * @param purchaseDate
     *            the date this movie was purchased
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
        // TODO: Change handling here
    }

    /**
     * This will return the custom description of this movie as
     * described by the owner
     * 
     * @return CustomDescription is the custom description of this
     *         movie
     */
    public String getCustomDescription() {
        if (this.customDescription == null) {
            controller.getMovieInfo(this);
        }
        return this.customDescription;
    }

    /**
     * This will set the custom description of this movie
     * 
     * @param customDescription
     *            the new custom description of this movie
     */
    public void setCustomDescription(String customDescription) {
        this.customDescription = customDescription;
        // TODO: Change handling here
    }

    /**
     * This will return the type of movie (i.e. VHS, DVD, BRAY, HDVD,
     * etc)
     * 
     * @return the type of this movie
     */
    public String getType() {
        if (this.type == null) {
            controller.getMovieInfo(this);
        }
        return this.type;
    }

    /**
     * This will change the type of this movie
     * 
     * @param type
     *            the new type of the movie.
     */
    public void setType(String type) {
        // TODO: input validation
        this.type = type;
    }

    /**
     * This will return the Owner of this movie
     * 
     * @return the owner of this movie
     */
    public Person getOwner() {
        // TODO: Should we store Owner or look it up?
        if (this.owner == null) {
            controller.getMovieInfo(this);
        }
        return this.owner;
    }

    /**
     * This will set to owner of this movie
     * 
     * @param owner
     *            the owner of this movie
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     * This will return the borrower of this movie (i.e. the person
     * currently in possession of the movie).
     * 
     * @return the person who is borrowing this movie (the owner if it
     *         isn't being borrowed.
     */
    public Person getBorrower() {
        if (this.borrower == null) {
            controller.getMovieInfo(this);
        }
        return this.borrower;
    }

    /**
     * This will set the current borrower (or possessor) of the movie.
     * 
     * @param borrower
     *            the borrower of the movie
     */
    public void setBorrower(Person borrower) {
        this.borrower = borrower;
    }

    /**
     * This will return a list of all of the genres that this movie
     * fits into.
     * 
     * @return the list of genres this movie is a part of.
     */
    public List<String> getGenres() {
        return controller.getGenresForMovie(this);
    }

    /**
     * Adds the genre specified to this movie
     * 
     * @param genre
     *            the genre to be added
     */
    public void addGenre(String genre) {
        controller.addGenreToMovie(this, genre);
    }

    /**
     * This will remove the specified genre from this movie.
     * 
     * @param genre
     *            the genre to be removed from this movie.
     */
    public void removeGenre(String genre) {
        controller.removeGenreFromMovie(this, genre);
    }

    /** 
     * This will return the condition this movie is in (e.g. "It's got a 
     * large scratch, and the case is broken")
     * 
     * @return the condition of the movie.
     * 
     */
    @Override
    public String getCondition() {
        //TODO: null handling
        return null;
    }
    
    /**
     * This will change the condition of the movie
     * 
     * @param condition the new condition of the movie
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

}
