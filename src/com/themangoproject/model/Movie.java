package com.themangoproject.model;

import java.util.Date;
import java.util.List;


public interface Movie {

    public String getTitle ();

    public String getDirector ();

    public String getRating ();

    public int getRuntime ();

    public int getYear ();

    public String getASIN ();

    public Date getPurchaseDate ();

    public String getCustomDescription ();

    public String getType ();

    public int getMangoRating ();

    public Person getOwner ();

    public Person getBorrower ();

    public List<String> getGenres ();

    public List<Actor> getActors ();

}

