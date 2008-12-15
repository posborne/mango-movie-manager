package com.themangoproject.model;

import java.awt.Image;
import java.util.Date;
import java.util.List;

import javax.swing.event.ChangeListener;

/**
 * This is a generic interface to represent a movie. A movie consists
 * of a large list of attributes.
 * 
 * @author Zachary Varberg, Paul Osborne
 */
public interface Movie {

    // getters
    /**
     * @return an Image that is a thumbnail for this movie
     */
    public Image getImage();

    /**
     * @return a String that is the title of this movie
     */
    public String getTitle();

    /**
     * @return a String that is the director of this movie
     */
    public String getDirector();

    /**
     * @return a String that is the rating (e.g. PG, R, PG-13) of this
     *         movie
     */
    public String getRating();

    /**
     * @return an int that is the runtime (in minutes) of this movie
     */
    public int getRuntime();

    /**
     * @return an int that is the year of this movie
     */
    public int getYear();

    /**
     * @return a String that is the ASIN of this movie
     */
    public String getASIN();

    /**
     * @return a String that is the condition of this movie
     */
    public String getCondition();

    /**
     * @return a Date that is the purchase date of this movie
     */
    public Date getPurchaseDate();

    /**
     * @return a String that is the custom description of this movie
     */
    public String getCustomDescription();

    /**
     * @return a String that is the type (e.g. DVD, VHS) of this movie
     */
    public String getType();

    /**
     * @return an int that is the mango rating (e.g. 0-5 mangoes) of
     *         this movie
     */
    public int getMangoRating();

    /**
     * @return a Person that is the owner of this movie
     */
    public Person getOwner();

    /**
     * @return a Person that is the borrower of this movie
     */
    public Person getBorrower();

    /**
     * @return a List of Strings that are the genres of this movie
     */
    public List<String> getGenres();

    /**
     * @return a List of Actors that played a role in this movie
     */
    public List<Actor> getActors();

    // setters
    /**
     * @param title
     *            the new title of this movie
     */
    public void setTitle(String title);

    /**
     * 
     * @param director
     *            the new director of this movie
     */
    public void setDirector(String director);

    /**
     * 
     * @param rating
     *            the new rating (e.g. PG, R, PG-13) of this movie
     */
    public void setRating(String rating);

    /**
     * 
     * @param minutes
     *            the new runtime (in minutes) of this movie
     */
    public void setRuntime(int minutes);

    /**
     * 
     * @param year
     *            the new year of this movie
     */
    public void setYear(int year);

    /**
     * 
     * @param asin
     *            the new ASIN of this movie
     */
    public void setASIN(String asin);

    /**
     * 
     * @param condition
     *            the new condition of this movie
     */
    public void setCondition(String condition);

    /**
     * 
     * @param purchaseDate
     *            the new purchase date of this movie
     */
    public void setPurchaseDate(Date purchaseDate);

    /**
     * 
     * @param customDescription
     *            the new custom description of this movie
     */
    public void setCustomDescription(String customDescription);

    /**
     * 
     * @param type
     *            the new type (e.g. DVD, VHS) of this movie
     */
    public void setType(String type);

    /**
     * 
     * @param mangoRating
     *            the new mango rating (e.g. 0-5 mangoes) of this
     *            movie
     */
    public void setMangoRating(int mangoRating);

    /**
     * 
     * @param owner
     *            the new owner of this movie
     */
    public void setOwner(Person owner);

    /**
     * 
     * @param borrower
     *            the new borrower of this movie
     */
    public void setBorrower(Person borrower);

    /**
     * This adds a genre to this movie.
     * 
     * @param genre
     *            the new genre to be added to this movie
     */
    public void addGenre(String genre);

    /**
     * This will remove a genre from this movie
     * 
     * @param genre
     *            the genre to be removed from this movie
     */
    public void removeGenre(String genre);

    // event listeners
    /**
     * This will add a ChangeListener to this movie.
     * 
     * @param l
     *            the ChangeListener to add to this movie
     */
    public void addChangeListener(ChangeListener l);

    /**
     * This will remove a ChangeListener from this movie.
     * 
     * @param l
     *            the ChangeListener to remove from this movie
     */
    public void removeChangeListener(ChangeListener l);

    /**
     * This will remove all ChangeListener from this movie.
     */
    public void removeAllChangeListeners();
}
