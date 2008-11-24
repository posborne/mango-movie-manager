package com.themangoproject.model;


public interface Role {

    public Actor getActor ();

    public String getCharacter ();

    public String getRole ();

    @Override
    public String toString ();

    public Movie getMovie ();

}

