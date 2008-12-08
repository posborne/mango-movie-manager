package com.themangoproject.model;

import java.util.Date;
import java.util.List;


public interface Movie {

	// getters
    public String getTitle ();
    public String getDirector ();
    public String getRating ();
    public int getRuntime ();
    public int getYear ();
    public String getASIN ();
    public String getCondition();
    public Date getPurchaseDate ();
    public String getCustomDescription ();
    public String getType ();
    public int getMangoRating ();
    public Person getOwner ();
    public Person getBorrower ();
    public List<String> getGenres ();
    public List<Actor> getActors ();

    // setters
    public void setTitle(String title);
    public void setDirector(String director);
    public void setRating(String rating);
    public void setRuntime(int minutes);
    public void setYear(int year);
    public void setASIN(String asin);
    public void setCondition(String condition);
    public void setPurchaseDate(Date purchaseDate);
    public void setCustomDescription(String customDescription);
    public void setType(String type);
    public void setMangoRating(int mangoRating);
    public void setOwner(Person owner);
    public void setBorrower(Person borrower);
    public void addGenre(String genre);
    public void removeGenre(String genre);
}

